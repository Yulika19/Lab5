package utils;

        import collection.*;
        import exceptions.InvalidFieldException;

        import java.io.*;
        import java.util.Vector;
        import java.util.Scanner;

/**
 * Основной класс для работы с csv файлом
 */
public class CSVFileWorker implements CSVFileWorkerInterface, FileWorker {
    private final CollectionManager manager;
    private String filePath;
    private String separator;
    private BufferedReader bufferedReader;

    public CSVFileWorker(CollectionManager collectionManager) {
        manager = collectionManager;
    }

    /**
     * Вспомогательный метод, реализующий ввод пути на считываемый csv файл.
     */
    @Override
    public void inputFilePath() {
        filePath = System.getenv("filePath");
        if (filePath == null) {
            print("Enter the path to file:");
            Scanner scan = new Scanner(System.in);
            filePath = scan.nextLine().trim();
            File file = new File(filePath);
            while (!file.exists() || file.isDirectory() || !file.canRead()) {
                if (!file.exists())
                    print("File isn't exist, please try again:");
                else if (!file.canRead())
                    print("Permission denied! File can't be read, please try again:");
                else
                    print("This is the Directory! Please try again:");
                filePath = scan.nextLine();
                file = new File(filePath.trim());
            }
        }
        try {
            bufferedReader = new BufferedReader(new FileReader(filePath));
        } catch (FileNotFoundException e) {
            print("File path is incorrect, please try again");
            return;
        }
        print("The path to the file is successfully entered");
    }

    /**
     * Парсит из массива строк в элемент коллекции
     *
     * @param values массив полей коллекции в строчном представлении
     * @return Эллемент коллекции
     */
    @Override
    public StudyGroup read(String[] values) {
        StudyGroupCreaterInterface studyGroupCreater = new StudyGroupCreater(bufferedReader, manager, false);
        for (int i = 0; i < values.length; i++) {
            values[i] = values[i].trim();
            if (values[i].isEmpty()) {
                print("You has empty field in the file!");
                return null;
            }
        }
        try {
            studyGroupCreater.setName(values[0]);

            if (InputChecker.checkDouble(values[1]))
                studyGroupCreater.setCoordinateX(Double.parseDouble(values[1]));
            else
                throw new InvalidFieldException("Coordinate X should be double");
            if (InputChecker.checkInt(values[2]))
                studyGroupCreater.setCoordinateY(Integer.parseInt(values[2]));
            else
                throw new InvalidFieldException("Coordinate Y should be Long");
            if (InputChecker.checkLong(values[3]))
                studyGroupCreater.setStudentsCount(Long.parseLong(values[3]));
            else
                throw new InvalidFieldException("Students count should be long and greater than 0");
            if (InputChecker.checkInt(values[4]))
                studyGroupCreater.setTransferredStudents(Integer.parseInt(values[4]));
            else
                throw new InvalidFieldException("Transferred students should be int and greater than 0");
            if (InputChecker.checkLong(values[5]))
                studyGroupCreater.setAverageMark(Long.parseLong(values[5]));
            else
                throw new InvalidFieldException("Average mark should be long and greater than 0");
            studyGroupCreater.setFormOfEducation((FormOfEducation) studyGroupCreater.checkStudyGroupEnum(values[6]));
            studyGroupCreater.setGAName(values[7]);
            if (InputChecker.checkDouble(values[8]))
                studyGroupCreater.setGAHeight(Double.parseDouble(values[8]));
            else
                throw new InvalidFieldException("Group Admin height should be double and greater than 0");
            studyGroupCreater.setGANationality((Country) studyGroupCreater.checkStudyGroupEnum(values[9]));
            if (InputChecker.checkLong(values[10]))
                studyGroupCreater.setGALocationX(Long.parseLong(values[10]));
            else
                throw new InvalidFieldException("Group Admin Location X should be Long");
            if (InputChecker.checkFloat(values[11]))
                studyGroupCreater.setGALocationY(Float.parseFloat(values[11]));
            else
                throw new InvalidFieldException("Group Admin Location Y should be float");
            if (InputChecker.checkFloat(values[12]))
                studyGroupCreater.setGALocationZ(Float.parseFloat(values[12]));
            else
                throw new InvalidFieldException("Group Admin Location Z should be Float");
            studyGroupCreater.setGALocationName(values[13]);
        } catch (InvalidFieldException e) {
            print(e.getMessage());
            return null;
        }
        return studyGroupCreater.getStudyGroup();
    }

    /**
     * Читает построчно файл csv и добавляет элементы в коллекцию по одному
     */
    @Override
    public void loadInput(Vector<StudyGroup> vector) {
        inputFilePath();
        File file = new File(filePath);
        if (!file.exists() || file.isDirectory() || !file.canRead()) {
            inputFilePathNoEnv();
        }
        try {
            bufferedReader = new BufferedReader(new FileReader(getFilePath().trim()));
            String line;
            setSeparator(",");
            while ((line = bufferedReader.readLine()) != null) {
                String[] lines = line.split(getSeparator());
                if (lines.length != 14 && lines.length != 17) {
                    print("You have the wrong number of fields, element will not added to the collection");
                    continue;
                }
                StudyGroup studyGroup = read(lines);
                if (studyGroup != null) {
                    print("Element " + studyGroup.getName() + " is successfully added to the collection");
                    vector.add(studyGroup);
                } else {
                    print("StudyGroup instance is not added to the collection");
                }
            }
        } catch (IOException e) {
            print("Incorrect file path\nFix the file");
        }
    }

    /**
     * Метод, обеспечивающий за строкове представление элементов коллекции в csv файле
     *
     * @param vector Коллекция
     * @return Строковое представление
     */
    @Override
    public String[] parseToString(Vector<StudyGroup> vector) {
        String[] toSave = new String[vector.size()];
        int cnt = 0;
        for (StudyGroup sg : vector) {
            toSave[cnt] = sg.getName();
            toSave[cnt] += ',' + String.valueOf(sg.getCoordinates().getX());
            toSave[cnt] += ',' + String.valueOf(sg.getCoordinates().getY());
            toSave[cnt] += ',' + String.valueOf(sg.getStudentsCount());
            toSave[cnt] += ',' + String.valueOf(sg.getTransferredStudents());
            toSave[cnt] += ',' + String.valueOf(sg.getAverageMark());
            toSave[cnt] += ',' + sg.getFormOfEducation().getUrl();
            toSave[cnt] += ',' + sg.getGroupAdmin().getName();
            toSave[cnt] += ',' + String.valueOf(sg.getGroupAdmin().getHeight());
            toSave[cnt] += ',' + String.valueOf(sg.getGroupAdmin().getNationality());
            toSave[cnt] += ',' + String.valueOf(sg.getGroupAdmin().getLocation().getX());
            toSave[cnt] += ',' + String.valueOf(sg.getGroupAdmin().getLocation().getY());
            toSave[cnt] += ',' + String.valueOf(sg.getGroupAdmin().getLocation().getZ());
            toSave[cnt] += ',' + sg.getGroupAdmin().getLocation().getName();
            toSave[cnt] += ',' + String.valueOf(sg.getId());
            toSave[cnt] += ',' + sg.getCreationDate().toString();
            toSave[cnt] += ',' + sg.getGroupAdmin().getBirthday().toString();
            cnt++;
        }
        return toSave;
    }

    /**
     * Метод, обеспечивающий запись в файл, использую BufferedWriter
     *
     * @param str
     */
    @Override
    public void write(String[] str) {
        try {
            String saveFilePath = System.getenv("testTTT");
            File file;
            if (saveFilePath == null) {
                print("The path in the environment variable is incorrect");
                inputFilePath();
                saveFilePath = getFilePath();
                while (!(file = new File(saveFilePath)).canWrite()) {
                    print("Permission denied! Unable to write to this file, please try again:");
                    inputFilePath();
                    saveFilePath = getFilePath();
                }
            }
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(saveFilePath));
            for (String s : str) {
                bufferedWriter.write(s);
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    /**
     * Получить значения разделителя, который используется для CSV файла.
     *
     * @return separator разделитель.
     */
    @Override
    public String getSeparator() {
        return separator;
    }

    /**
     * Установить значение разделителя, которое будет использоваться для CSV файла.
     *
     * @param separator разделитель.
     */
    @Override
    public void setSeparator(String separator) {
        this.separator = separator;
    }

    /**
     * @return filePath
     */
    @Override
    public String getFilePath() {
        return filePath;
    }

    /**
     * Обеспечивает общение FileWorker и клиента
     *
     * @param message
     */
    public void print(String message) {
        System.out.println(message);
    }

    private void inputFilePathNoEnv() {
        print("Enter the path to file:");
        Scanner scan = new Scanner(System.in);
        filePath = scan.nextLine().trim();
        File file = new File(filePath);
        while (!file.exists() || file.isDirectory() || !file.canRead()) {
            if (!file.exists())
                print("File isn't exist, please try again:");
            else if (!file.canRead())
                print("Permission denied! File can't be read, please try again:");
            else
                print("This is the Directory! Please try again:");
            filePath = scan.nextLine();
            file = new File(filePath.trim());
        }
        try {
            bufferedReader = new BufferedReader(new FileReader(filePath));
        } catch (FileNotFoundException e) {
            print("File path is incorrect, please try again");
            return;
        }
        print("The path to the file is successfully entered");
    }
}
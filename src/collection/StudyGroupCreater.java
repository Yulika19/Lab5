package collection;

        import exceptions.InvalidFieldException;
        import utils.IdGenerator;
        import utils.InputChecker;

        import java.io.BufferedReader;
        import java.io.IOException;
        import java.time.ZonedDateTime;

/**
 * Класс, обеспечивающий ввод элементов StudyGroups
 * Обеспечивает валидность вводимых полей
 */
public class StudyGroupCreater implements StudyGroupCreaterInterface {
    private final CollectionManager manager;
    private final boolean isScript;
    private BufferedReader reader;
    private String line;
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private long studentsCount; //Значение поля должно быть больше 0
    private int transferredStudents; //Значение поля должно быть больше 0
    private long averageMark; //Значение поля должно быть больше 0
    private FormOfEducation formOfEducation; //Поле не может быть null
    private Person groupAdmin; //Поле может быть null

    public StudyGroupCreater(BufferedReader reader, CollectionManager manager, boolean isScript) {
        this.manager = manager;
        this.reader = reader;
        this.isScript = isScript;
    }

    @Override
    public void setName(String name) throws InvalidFieldException {
        if (name == null || name.equals(""))
            throw new InvalidFieldException("Invalid value for StudyGroup name");
        else
            this.name = name;
    }

    @Override
    public void setCoordinateX(double x) throws InvalidFieldException {
        if (coordinates == null)
            this.coordinates = new Coordinates();
        coordinates.setX(x);
    }

    @Override
    public void setCoordinateY(Integer y) throws InvalidFieldException {
        if (y == null || y > 287)
            throw new InvalidFieldException("Invalid value for StudyGroup Coordinate y");
        if (coordinates == null)
            this.coordinates = new Coordinates();
        coordinates.setY(y);
    }

    @Override
    public void setStudentsCount(long studentsCount) throws InvalidFieldException {
        if (studentsCount <= 0)
            throw new InvalidFieldException("Invalid value for StudyGroup studentsCount");
        else
            this.studentsCount = studentsCount;
    }

    @Override
    public void setTransferredStudents(int transferredStudents) throws InvalidFieldException {
        if (transferredStudents <= 0)
            throw new InvalidFieldException("Invalid value for StudyGroup transferredStudents");
        else
            this.transferredStudents = transferredStudents;
    }

    @Override
    public void setAverageMark(long averageMark) throws InvalidFieldException {
        if (averageMark <= 0)
            throw new InvalidFieldException("Invalid value for StudyGroup averageMark");
        else
            this.averageMark = averageMark;
    }

    @Override
    public void setFormOfEducation(FormOfEducation formOfEducation) throws InvalidFieldException {
        if (formOfEducation == null)
            throw new InvalidFieldException("Invalid value for StudyGroup formOfEducation");
        else
            this.formOfEducation = formOfEducation;

    }

    @Override
    public void setGAName(String name) throws InvalidFieldException {
        if (name == null || name.equals(""))
            throw new InvalidFieldException("Invalid value for StudyGroup GroupAdmin name");
        else {
            if (groupAdmin == null)
                groupAdmin = new Person();
            groupAdmin.setName(name);
        }

    }

    @Override
    public void setGAHeight(double height) throws InvalidFieldException {
        if (height <= 0)
            throw new InvalidFieldException("Invalid value for StudyGroup GroupAdmin height");
        else if (groupAdmin == null)
            groupAdmin = new Person();
        groupAdmin.setHeight(height);
    }

    @Override
    public void setGANationality(Country nationality) {
        groupAdmin.setNationality(nationality);
    }

    @Override
    public void setGALocation(Location location) throws InvalidFieldException {
        if (location == null)
            throw new InvalidFieldException("Invalid value for StudyGroup GroupAdmin location");
        else if (groupAdmin == null)
            groupAdmin = new Person();
        groupAdmin.setLocation(location);
    }

    @Override
    public void setGALocationX(Long x) throws InvalidFieldException {
        if (groupAdmin.getLocation() == null)
            setGALocation(new Location());
        else if (x == null)
            throw new InvalidFieldException("Invalid value for StudyGroup GroupAdmin Location x");
        groupAdmin.getLocation().setX(x);
    }

    @Override
    public void setGALocationY(float y) throws InvalidFieldException {
        if (groupAdmin.getLocation() == null)
            setGALocation(new Location());
        groupAdmin.getLocation().setY(y);
    }

    @Override
    public void setGALocationZ(Float z) throws InvalidFieldException {
        if (groupAdmin.getLocation() == null)
            setGALocation(new Location());
        else if (z == null)
            throw new InvalidFieldException("Invalid value for StudyGroup GroupAdmin Location z");
        groupAdmin.getLocation().setZ(z);
    }

    @Override
    public void setGALocationName(String gAName) throws InvalidFieldException {
        if (groupAdmin.getLocation() == null)
            groupAdmin.setLocation(new Location());
        else if (gAName == null)
            throw new InvalidFieldException("Invalid value for StudyGroup GroupAdmin Location name");
        groupAdmin.getLocation().setName(gAName);
    }

    @Override
    public StudyGroup getStudyGroup() {
        return new StudyGroup(name, coordinates, studentsCount, transferredStudents, averageMark, formOfEducation, groupAdmin, IdGenerator.generateId(manager));
    }

    /**
     * Обработка ввода Enum'ов StudyGroup
     *
     * @param str
     * @return 'Country' or 'FormOfEducation' or 'null'
     */
    @Override
    public Enum checkStudyGroupEnum(String str) throws InvalidFieldException {
        for (Country cntr : Country.values()) {
            if (str.equalsIgnoreCase(cntr.getUrl())) {
                return cntr;
            }
        }
        for (FormOfEducation fOE : FormOfEducation.values()) {
            if (str.equalsIgnoreCase(fOE.getUrl())) {
                return fOE;
            }
        }
        throw new InvalidFieldException("There is no enum named " + str);
    }

    @Override
    public void askGALocationName() {

    }

    /**
     * Обработка ввода Id Study Group
     *
     * @return Integer id
     */
    @Override
    public Integer askStudyGroupId() {
        String s;
        try {
            System.out.print("Input Study group id: ");
            s = reader.readLine();
            if (InputChecker.checkInt(s.trim()))
                return Integer.parseInt(s);
            else
                System.out.println("Study group id can't be null; should be int");
        } catch (IOException e) {
            System.out.println("Input error");
        }
        return null;
    }

    /**
     * Ввод всех полей
     */
    public void inputFieldsFile() {
        askName();
        askCoordinateX();
        askCoordinateY();
        askStudentsCount();
        askTransferredStudents();
        askAverageMark();
        askFormOfEducation();
        askGAName();
        askGAHeight();
        askGANationality();
        askGALocationX();
        askGALocationY();
        askGALocationZ();
        askGALocationName();
    }

    public void askName() {
        print("Input Study Group Name: ");
        try {
            setName(inputLine());
        } catch (InvalidFieldException e) {
            println("Study Group Name should be String, can't be null");
            if (!isScript)
                askName();
        }
    }

    public void askCoordinateX() {
        print("Input Study Group Coordinate X: ");
        try {
            setCoordinateX(Double.parseDouble(inputLine()));
        } catch (InvalidFieldException | NumberFormatException e) {
            println("Study Group Coordinate X should be double");
            if (!isScript)
                askCoordinateX();
        }
    }

    public void askCoordinateY() {
        print("Input Study Group Coordinate Y: ");
        try {
            setCoordinateY(Integer.parseInt(inputLine()));
        } catch (InvalidFieldException | NumberFormatException e) {
            println("Study Group Coordinate Y should be Integer and less than 287, can't be null");
            if (!isScript)
                askCoordinateY();
        }
    }

    public void askStudentsCount() {
        print("Input Study Group students count: ");
        try {
            setStudentsCount(Long.parseLong(inputLine()));
        } catch (InvalidFieldException | NumberFormatException e) {
            println("Study Group students count should be long and greater than 0");
            if (!isScript)
                askStudentsCount();
        }
    }


    public void askTransferredStudents() {
        print("Input Study Group transferred students count: ");
        try {
            setTransferredStudents(Integer.parseInt(inputLine()));
        } catch (InvalidFieldException | NumberFormatException e) {
            println("Study Group transferred students count should be int and greater than 0");
            if (!isScript)
                askTransferredStudents();
        }
    }

    public void askAverageMark() {
        print("Input Study Group students average mark: ");
        try {
            setAverageMark(Long.parseLong(inputLine()));
        } catch (InvalidFieldException | NumberFormatException e) {
            println("Study Group students average mark should be long and greater than 0");
            if (!isScript)
                askAverageMark();
        }
    }

    public void askFormOfEducation() {
        FormOfEducation.printValues();
        print("Input Study Group Form Of Education: ");
        try {
            setFormOfEducation((FormOfEducation) checkStudyGroupEnum(inputLine()));
        } catch (InvalidFieldException e) {
            println("There is no in Study Group Form Of Education value: " + line + "\nField Form Of Education can't be null");
            if (!isScript)
                askFormOfEducation();
        }
    }


    public void askGAName() {
        print("Input Study Group Group Admin name: ");
        try {
            setGAName(inputLine());
        } catch (InvalidFieldException e) {
            println("Study Group Group Admin name can't be void or null");
            if (!isScript)
                askGAName();
        }
    }

    public void askGAHeight() {
        print("Input Study Group Group Admin height: ");
        try {
            setGAHeight(Double.parseDouble(inputLine()));
        } catch (InvalidFieldException | NumberFormatException e) {
            println("Study Group Group Admin height should be double and greater than 0");
            if (!isScript)
                askGAHeight();
        }
    }

    public void askGANationality() {
        Country.printValues();
        print("Input Study Group Group Admin nationality: ");
        try {
            setGANationality((Country) checkStudyGroupEnum(inputLine()));
        } catch (InvalidFieldException e) {
            println("There is no in Study Group Form Of Education value: " + line + "\nField Form Of Education can't be null");
            if (!isScript)
                askFormOfEducation();
        }
    }

    public void askGALocationX() {
        print("Input Study Group Group Admin Location X: ");
        try {
            setGALocationX(Long.parseLong(inputLine()));
        } catch (NumberFormatException | InvalidFieldException e) {
            println("Study Group Group Admin Location should be Long, can't be null");
            if (!isScript)
                askGALocationX();
        }

    }

    public void askGALocationY() {
        print("Input Study Group Group Admin Location Y: ");
        try {
            setGALocationY(Float.parseFloat(inputLine()));
        } catch (NumberFormatException | InvalidFieldException e) {
            println("Study Group Group Admin Location should be float");
            if (!isScript)
                askGALocationY();
        }

    }

    public void askGALocationZ() {
        print("Input Study Group Group Admin Location Z: ");
        try {
            setGALocationZ(Float.parseFloat(inputLine()));
        } catch (NumberFormatException | InvalidFieldException e) {
            println("Study Group Group Admin Location should be Float, can't be null");
            if (!isScript)
                askGALocationZ();
        }
    }

    /**
     * Прочитать строку
     *
     * @return line
     */
    public String inputLine() {
        try {
            line = reader.readLine().toLowerCase().trim();
        } catch (IOException ioException) {
            println(ioException.getMessage());
        }
        return line;
    }

    /**
     * Обеспечивает ввод всех полей и передачу экземпляра StudyGroup
     *
     * @return studyGroup
     */
    public StudyGroup askStudyGroup() {
        inputFieldsFile();
        return getStudyGroup();
    }

    /**
     * Обеспечивает общение StudyGroupCreater с переносом строки
     *
     * @param message
     */
    public void println(String message) {
        System.out.println(message);
    }

    /**
     * Обеспеичвает общение StudyGroupCreater без переноса строки
     *
     * @param message
     */
    public void print(String message) {
        System.out.print(message);
    }

    /**
     * Setter
     *
     * @param reader
     */
    public void setReader(BufferedReader reader) {
        this.reader = reader;
    }
}
package collection;

        import java.util.*;

/**
 * Класс который хранит коллекцию и совершает действия с ней
 */
public class CollectionManager {
    private final Vector<StudyGroup> studyGroups;
    private final Date creationDate;
    private final HashSet<Integer> idList;

    public CollectionManager() {
        studyGroups = new Vector<>();
        idList = new HashSet<>();
        creationDate = new Date();
    }

    /**
     * Метод отвечающий за полную очистку коллекции
     */
    public void clear() {
        studyGroups.clear();
        print("The collection successfully cleared", true);
    }

    /**
     * Метод, отвечающий за подсчет среднего количества переведенных студентов
     */
    public int averageOfTransferredStudents() {
        int ans = 0;
        for (StudyGroup it : studyGroups) {
            ans += it.getTransferredStudents();
        }
        if(studyGroups.size()!=0) {
            ans = ans / studyGroups.size();
            print("Average of transferred students is: " + ans, true);
        }
        else
            print("There is no elements in the collection",true);
        return ans;
    }

    /**
     * Метод, отвечающий за удаление первого элемента коллекции
     */
    public void removeFirstElement() {
        if (!studyGroups.isEmpty()) {
            studyGroups.remove(studyGroups.firstElement());
            print("The first element is successfully removed from collection", true);
        } else
            print("There is no elements in the collection.\nElement is not removed from collection", true);
    }

    /**
     * Метод, отвечающий за удаление последнего элемента коллекции
     */
    public void removeLastElement() {
        if (!studyGroups.isEmpty()) {
            studyGroups.remove(studyGroups.size()-1);
            print("The last element is successfully removed from collection", true);
        } else
            print("There is no elements in the collection.\nElement is not removed from collection", true);
    }

    /**
     * Метод, отвечающий за удаление элемента коллекции по id
     *
     * @param id
     */
    public void removeById(int id) {
        if (studyGroups.removeIf(x -> x.getId() == id))
            print("Element is successfully removed from collection", true);
        else
            print("Element is not removed from collection", true);
    }

    /**
     * Считает, сколько средних оценок, выше заданного
     *
     * @param averageMark
     */
    public void countGreaterThanAverageMark(long averageMark) {
        int count = 0;
        for (StudyGroup it : studyGroups) {
            if (it.getAverageMark() > averageMark) {
                count++;
            }
        }
        print("Count of elements is: " + count, true);
    }

    /**
     * Метод, отвечающий за добавление элемента в коллекцию
     *
     * @param studyGroup
     * @return был ли добавлен элемент
     */
    public boolean addElement(StudyGroup studyGroup) {
        if (studyGroups.contains(studyGroup)) {
            print("Element is already exist in collection!", true);
            return false;
        } else {
            studyGroups.add(studyGroup);
            print("Element is added successfully!", true);
            return true;
        }
    }

    /**
     * @param id         айди группы, по которому произойдет обновление
     * @param studyGroup обновленный экземпляр
     * @return Произошло ли обновление
     */
    public boolean update(int id, StudyGroup studyGroup) {
        for (StudyGroup sg : studyGroups) {
            if (sg.getId() == id) {
                removeById(id);
                studyGroup.setId(id);
                studyGroups.add(studyGroup);
                print("Element is updated!", true);
                return true;
            }
        }
        print("Input id is not found!", true);
        return false;
    }

    /**
     * Метод выводит краткую информацию о классе
     */
    public void info() {
        String info = "Время инциализации коллекции: " + getInitializationTime() + "\n" +
                "Количество элементов в коллекции: " + studyGroups.size() + "\n" +
                "Тип коллекции: " + studyGroups.getClass().getSimpleName();
        print(info, true);
    }

    /**
     * Метод, обеспечивающий сортировку коллекции
     */
    public void sort() {
        Collections.sort(studyGroups);
    }

    /**
     * Метод возвращающий дату создания обьекта.
     *
     * @return дата создания обькта.
     */
    public Date getInitializationTime() {
        return creationDate;
    }

    /**
     * Проверяет, есть ли элемент с данным id
     *
     * @param id
     * @return true - если элемент с данным id существует
     */
    public boolean containsId(int id) {
        for (StudyGroup st : studyGroups) {
            if (st.getId() == id)
                return true;
        }
        return false;
    }

    /**
     * @return idList
     */
    public HashSet<Integer> getIdList() {
        return idList;
    }

    /**
     * @return studyGroups
     */
    public Vector<StudyGroup> getStudyGroups() {
        return studyGroups;
    }

    /**
     * Получить Comparator сравнения по id
     *
     * @return
     */
    public Comparator<StudyGroup> getComparatorById() {
        return (StudyGroup a, StudyGroup b) -> (int) (a.getId() - b.getId());
    }

    /**
     * Метод, обеспечивающий вывод строки
     *
     * @param str      выведится введенная строка
     * @param printMod булевая переменная, отвечаающая за наличие переноса строки
     */
    public void print(String str, boolean printMod) {
        if (!printMod)
            System.out.print(str);
        else
            System.out.println(str);
    }
}
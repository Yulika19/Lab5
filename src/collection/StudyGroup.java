package collection;

        import java.time.ZonedDateTime;
        import java.util.Objects;

public class StudyGroup implements Comparable<StudyGroup> {
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private long studentsCount; //Значение поля должно быть больше 0
    private int transferredStudents; //Значение поля должно быть больше 0
    private long averageMark; //Значение поля должно быть больше 0
    private FormOfEducation formOfEducation; //Поле не может быть null
    private Person groupAdmin; //Поле может быть null

    public StudyGroup(String name,
                      Coordinates coordinates,
                      long studentsCount,
                      int transferredStudents,
                      long averageMark,
                      FormOfEducation formOfEducation,
                      Person groupAdmin,
                      int id) {
        this.name = name;
        this.coordinates = coordinates;
        this.studentsCount = studentsCount;
        this.transferredStudents = transferredStudents;
        this.averageMark = averageMark;
        this.formOfEducation = formOfEducation;
        this.groupAdmin = groupAdmin;
        this.id = id;
        this.creationDate = ZonedDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public long getStudentsCount() {
        return studentsCount;
    }

    public void setStudentsCount(long studentsCount) {
        this.studentsCount = studentsCount;
    }

    public int getTransferredStudents() {
        return transferredStudents;
    }

    public void setTransferredStudents(int transferredStudents) {
        this.transferredStudents = transferredStudents;
    }

    public long getAverageMark() {
        return averageMark;
    }

    public void setAverageMark(long averageMark) {
        this.averageMark = averageMark;
    }

    public FormOfEducation getFormOfEducation() {
        return formOfEducation;
    }

    public void setFormOfEducation(FormOfEducation formOfEducation) {
        this.formOfEducation = formOfEducation;
    }

    public Person getGroupAdmin() {
        return groupAdmin;
    }

    public void setGroupAdmin(Person groupAdmin) {
        this.groupAdmin = groupAdmin;
    }

    @Override
    public String toString() {
        return "StudyGroup{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates.toString() +
                ", creationDate=" + creationDate.getHour() + ':' + creationDate.getMinute() + ':' + creationDate.getSecond() + ' ' + creationDate.getDayOfMonth() + ' ' + creationDate.getMonth() + ' ' + creationDate.getYear() +
                ", studentsCount=" + studentsCount +
                ", transferredStudents=" + transferredStudents +
                ", averageMark=" + averageMark +
                ", formOfEducation=" + formOfEducation +
                ", groupAdmin=" + groupAdmin.toString() +
                '}';
    }

    @Override
    public int compareTo(StudyGroup o) {
        return (int) (getId() - o.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudyGroup that = (StudyGroup) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
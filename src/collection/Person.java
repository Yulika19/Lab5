package collection;

        import java.time.LocalDate;

public class Person {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private LocalDate birthday; //Поле может быть null
    private double height; //Значение поля должно быть больше 0
    private Country nationality; //Поле может быть null
    private Location location; //Поле не может быть null

    public Person(String name, double height, Country nationality, Location location) {
        this.name = name;
        this.height = height;
        this.nationality = nationality;
        this.location = location;
        this.birthday = LocalDate.now();
    }

    public Person() {
        birthday = LocalDate.now();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Country getNationality() {
        return nationality;
    }

    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", birthday=" + birthday.getDayOfMonth() + ' ' + birthday.getMonth() + ' ' + birthday.getYear() +
                ", height=" + height  +
                ", nationality='" + nationality + '\'' +
                ", location=" + location.toString() +
                '}';
    }
}
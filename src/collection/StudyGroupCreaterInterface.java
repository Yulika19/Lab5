package collection;

        import exceptions.InvalidFieldException;

public interface StudyGroupCreaterInterface {
    void setName(String name) throws InvalidFieldException;

    void setCoordinateX(double x) throws InvalidFieldException;

    void setCoordinateY(Integer y) throws InvalidFieldException;

    void setStudentsCount(long studentsCount) throws InvalidFieldException;

    void setTransferredStudents(int transferredStudents) throws InvalidFieldException;

    void setAverageMark(long averageMark) throws InvalidFieldException;

    void setFormOfEducation(FormOfEducation formOfEducation) throws InvalidFieldException;

    void setGAName(String name) throws InvalidFieldException;

    void setGAHeight(double height) throws InvalidFieldException;

    void setGANationality(Country nationality) throws InvalidFieldException;

    void setGALocation(Location location) throws InvalidFieldException;

    void setGALocationX(Long x) throws InvalidFieldException;

    void setGALocationY(float y) throws InvalidFieldException;

    void setGALocationZ(Float z) throws InvalidFieldException;

    void setGALocationName(String name) throws InvalidFieldException;

    StudyGroup getStudyGroup();

    Enum checkStudyGroupEnum(String str) throws InvalidFieldException;

    Integer askStudyGroupId();

    void inputFieldsFile();

    void askName();

    void askCoordinateX();

    void askCoordinateY();

    void askStudentsCount();

    void askTransferredStudents();

    void askAverageMark();

    void askFormOfEducation();

    void askGAName();

    void askGAHeight();

    void askGANationality();

    void askGALocationX();

    void askGALocationY();

    void askGALocationZ();

    void askGALocationName();

    StudyGroup askStudyGroup();
}
package utils;

        import collection.StudyGroup;

        import java.util.Vector;

public interface FileWorker {
    String getFilePath();

    void inputFilePath();

    void write(String[] str);

    StudyGroup read(String[] values);

    void loadInput(Vector<StudyGroup> vector);

    String[] parseToString(Vector<StudyGroup> vector);
}
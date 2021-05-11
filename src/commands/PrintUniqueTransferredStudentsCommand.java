package commands;

        import collection.CollectionManager;
        import collection.StudyGroup;

        import java.util.HashSet;
        import java.util.Set;

public class PrintUniqueTransferredStudentsCommand extends AbstractCommand {
    private final CollectionManager collectionManager;

    public PrintUniqueTransferredStudentsCommand(CollectionManager collectionManager) {
        super("print_unique_transferred_students", " : вывести уникальные значения поля transferredStudents всех элементов в коллекции");
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        collectionManager.sort();
        Set<StudyGroup> set = new HashSet<>();
        set.addAll(collectionManager.getStudyGroups());
        StringBuilder sb = new StringBuilder("Элементов в коллекции: " + collectionManager.getStudyGroups().size()).append("\n");
        for(StudyGroup sg : set){
            sb.append(sg.getTransferredStudents()+"\n");
        }
        System.out.println(sb.toString());
    }
}
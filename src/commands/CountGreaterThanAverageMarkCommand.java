package commands;

        import collection.CollectionManager;
        import collection.StudyGroup;
        import collection.StudyGroupCreater;
        import collection.StudyGroupCreaterInterface;
        import exceptions.InvalidFieldException;

public class CountGreaterThanAverageMarkCommand extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final StudyGroupCreaterInterface creator;

    public CountGreaterThanAverageMarkCommand(CollectionManager collectionManager, StudyGroupCreaterInterface creator) {
        super("count_greater_than_average_mark", " averageMark : вывести количество элементов, значение поля averageMark которых больше заданного");
        this.collectionManager = collectionManager;
        this.creator = creator;
    }

    @Override
    public void execute(String[] args) {
        long averageMark;
        creator.askAverageMark();
        averageMark = creator.getStudyGroup().getAverageMark();
        int ans = 0;
        for (StudyGroup sg : collectionManager.getStudyGroups()) {
            if (sg.getAverageMark() > averageMark)
                ans++;
        }
        System.out.println("Ans is: "+ans);
    }
}
package commands;

        import collection.CollectionManager;
        import collection.StudyGroupCreater;
        import collection.StudyGroupCreaterInterface;
        import utils.InputChecker;

public class RemoveByIdCommand extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final StudyGroupCreaterInterface studyGroupCreater;

    public RemoveByIdCommand(CollectionManager collectionManager, StudyGroupCreaterInterface studyGroupCreater) {
        super("remove_by_id", " : удалить элемент коллекции по его id");
        this.collectionManager = collectionManager;
        this.studyGroupCreater = studyGroupCreater;
    }

    @Override
    public void execute(String[] args) {
        Integer id;
        if (args.length > 1 && args[1].length() > 0 && InputChecker.checkLong(args[1].trim())) {
            id = Integer.parseInt(args[1].trim());
        } else {
            id = studyGroupCreater.askStudyGroupId();
        }
        if (id != null && collectionManager.containsId(id))
            collectionManager.removeById(id);
        else {
            System.out.println("Данный id не найден");
        }
    }
}
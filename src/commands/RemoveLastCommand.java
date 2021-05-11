package commands;

        import collection.CollectionManager;

public class RemoveLastCommand extends AbstractCommand {
    private final CollectionManager collectionManager;

    public RemoveLastCommand(CollectionManager collectionManager) {
        super("remove_last", " : удалить последний элемент из коллекции");
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        collectionManager.removeLastElement();
    }
}
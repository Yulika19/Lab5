package commands;

        import collection.CollectionManager;

public class SortCommand extends AbstractCommand {
    private final CollectionManager collectionManager;

    public SortCommand(CollectionManager collectionManager) {
        super("sort", " : отсортировать коллекцию в естественном порядке");
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        collectionManager.sort();
        System.out.println("Collection is successfully sorted");
    }
}
package commands;

import collection.CollectionManager;

public class AverageOfTransferredStudentsCommand extends AbstractCommand {
    private final CollectionManager collectionManager;

    public AverageOfTransferredStudentsCommand(CollectionManager collectionManager) {
        super("average_of_transferred_students", " :  вывести среднее значение поля transferredStudents для всех элементов коллекции");
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        collectionManager.averageOfTransferredStudents();
    }
}
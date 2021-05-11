package client;

        import collection.CollectionManager;
        import collection.StudyGroupCreater;
        import collection.StudyGroupCreaterInterface;
        import commands.*;

        import java.io.BufferedReader;
        import java.io.InputStreamReader;

/**
 * Класс клиента
 */
public class Client {
    private final CommandReaderInterface commandReader;
    private boolean isRunning = true;

    public Client(CommandReaderInterface commandReader) {
        this.commandReader = commandReader;
        CollectionManager manager = commandReader.getManager();
        StudyGroupCreaterInterface studyGroupCreater = new StudyGroupCreater(new BufferedReader(new InputStreamReader(System.in)), manager, false);
        commandReader.addCommand("add", new AddCommand(manager, studyGroupCreater));
        commandReader.addCommand("average_of_transferred_students", new AverageOfTransferredStudentsCommand(manager));
        commandReader.addCommand("clear", new ClearCommand(manager));
        commandReader.addCommand("count_greater_than_average_mark", new CountGreaterThanAverageMarkCommand(manager, studyGroupCreater));
        commandReader.addCommand("execute_script", new ExecuteScriptCommand(manager));
        commandReader.addCommand("exit", new ExitCommand(this));
        commandReader.addCommand("info", new InfoCommand(manager));
        commandReader.addCommand("print_unique_transferred_students", new PrintUniqueTransferredStudentsCommand(manager));
        commandReader.addCommand("remove_by_id", new RemoveByIdCommand(manager, studyGroupCreater));
        commandReader.addCommand("remove_first", new RemoveFirstCommand(manager));
        commandReader.addCommand("remove_last", new RemoveLastCommand(manager));
        commandReader.addCommand("save", new SaveCommand(manager));
        commandReader.addCommand("show", new ShowCommand(manager));
        commandReader.addCommand("sort", new SortCommand(manager));
        commandReader.addCommand("update", new UpdateCommand(manager, studyGroupCreater));
        commandReader.addCommand("help", new HelpCommand(commandReader.getCommandMap()));
    }

    /**
     * Начинает работу приложения
     */
    public final void interactiveMode() {
        commandReader.start();
        while (isRunning) commandReader.readCommand();
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }
}
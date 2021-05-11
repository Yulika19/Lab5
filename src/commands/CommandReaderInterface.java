package commands;

        import collection.CollectionManager;

        import java.util.HashMap;

public interface CommandReaderInterface {

    void start();

    void readCommand();

    void addCommand(String commandName, AbstractCommand command);

    CollectionManager getManager();

    HashMap<String, AbstractCommand> getCommandMap();

}
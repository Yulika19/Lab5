import client.Client;
import collection.CollectionManager;
import commands.CommandReader;
import utils.CSVFileWorker;

public class Main {

    public static void main(String[] args) {
        CollectionManager manager = new CollectionManager();
        CSVFileWorker fileWorker = new CSVFileWorker(manager);
        CommandReader commandReader = new CommandReader(manager, fileWorker);
        Client client = new Client(commandReader);
        client.interactiveMode();
    }
}
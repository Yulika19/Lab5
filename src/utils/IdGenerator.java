package utils;

        import collection.CollectionManager;

        import java.util.HashSet;

public interface IdGenerator {
    static int generateId(CollectionManager collectionManager) {
        HashSet<Integer> idList = collectionManager.getIdList();
        int id = (int) (Math.random() * 666666);
        while (idList.contains(id)) {
            id = (int) (Math.random() * 666666);
        }
        idList.add(id);
        return id;
    }
}
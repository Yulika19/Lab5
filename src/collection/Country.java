package collection;

public enum Country {
    RUSSIA("RUSSIA",1),
    USA("USA", 2),
    FRANCE("FRANCE", 3),
    ITALY("ITALY", 4),
    NORTH_KOREA("NORTH_KOREA", 5);

    private final int value;
    private final String url;

    Country(String url, int value) {
        this.value = value;
        this.url = url;
    }

    public static void printValues() {
        System.out.println("List of COUNTRY enum values:");
        for (Country cntr : Country.values()) {
            System.out.println(cntr.getUrl());
        }
    }

    public int getValue() {
        return value;
    }

    public String getUrl() {
        return url;
    }
}
interface Item {
    String name = "";
    String description = "";

    default String getName() {
        return name;
    }

    default String getDescription() {
        return description;
    }
}

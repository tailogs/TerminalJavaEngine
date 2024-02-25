public class _Map {
    public _Map() {}
    // Пока публичный, но потом надо скрыть
    public String[][] _map = {
            {"#", "#", "#", "#", "#", "#", "#", "#", "#", "#"},
            {"#", " ", " ", " ", " ", " ", "$", " ", " ", "#"},
            {"#", " ", " ", "!", " ", " ", " ", " ", " ", "#"},
            {"#", " ", " ", " ", " ", " ", " ", "!", " ", "#"},
            {"#", "A", " ", " ", "@", " ", " ", " ", " ", "#"},
            {"#", " ", " ", " ", " ", " ", " ", " ", " ", "#"},
            {"#", " ", " ", "!", " ", " ", " ", "A", " ", "#"},
            {"#", " ", " ", " ", " ", " ", " ", " ", " ", "#"},
            {"#", " ", "A", " ", " ", " ", " ", " ", " ", "#"},
            {"#", "#", "#", "#", "#", "#", "#", "#", "#", "#"},
    };

    public String[][] getMap() {
        return _map;
    }

    public void setMap(String[][] map) { _map = map;
    }

    // Вывод обозначений символов с цветом
    public void printLegend() {
        System.out.println(Color.RED + "# - стены" + Color.RESET);
        System.out.println(Color.GREEN + "@ - игрок" + Color.RESET);
        System.out.println(Color.YELLOW + "$ - союзник" + Color.RESET);
        System.out.println(Color.BLUE + "A - враг" + Color.RESET);
        System.out.println(Color.PURPLE + "! - ловушка или интересный элемент" + Color.RESET);
    }

    // Вывод карты с заданными цветами для различных символов
    public void printMap() {
        for (String[] row : _map) {
            for (String cell : row) {
                switch (cell) {
                    case "#":
                        System.out.print(Color.WHITE + cell + " " + Color.RESET);
                        break;
                    case "@":
                        System.out.print(Color.GREEN + cell + " " + Color.RESET);
                        break;
                    case "$":
                        System.out.print(Color.YELLOW + cell + " " + Color.RESET);
                        break;
                    case "A":
                        System.out.print(Color.BLUE + cell + " " + Color.RESET);
                        break;
                    case "!":
                        System.out.print(Color.PURPLE + cell + " " + Color.RESET);
                        break;
                    default:
                        System.out.print(cell + " ");
                }
            }
            System.out.println();
        }
    }
}

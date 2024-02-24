import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    enum Color {
        RESET("\u001B[0m"),
        BLACK("\u001B[30m"),
        RED("\u001B[31m"),
        GREEN("\u001B[32m"),
        YELLOW("\u001B[33m"),
        BLUE("\u001B[34m"),
        PURPLE("\u001B[35m"),
        CYAN("\u001B[36m"),
        WHITE("\u001B[37m");

        private final String code;

        Color(String code) {
            this.code = code;
        }

        @Override
        public String toString() {
            return code;
        }
    }

    String[][] map = {
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

    String textError = "[ Все в порядке ]";
    String plot = "Тут описывается короткий сюжет именно в этот момент, несколько строк.";
    int lvlPlayer, expCurrent, expRequired = 50;
    Map<String, Integer> inventory = new HashMap<>(); // Инвентарь

    int playerX = 4;
    int playerY = 4;

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
        for (String[] row : map) {
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

    // Основной метод игры
    public void playGame() {
        Scanner scanner = new Scanner(System.in);

        // Главный игровой цикл
        while (true) {
            // Очистка консоли (просто добавим пустые строки)
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }

            printMap();
            printLegend();


            System.out.println(Color.CYAN + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + Color.RESET);
            System.out.println(Color.BLUE + "Ваш уровень: " + lvlPlayer + " [" + expCurrent + "/" + expRequired + "]");
            System.out.print(Color.BLUE + "Ваш инвентарь: ");
            showInventory();
            System.out.println(Color.CYAN + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + Color.RESET);
            System.out.println(Color.CYAN + "Сюжет:" + Color.RESET);
            System.out.println(plot);
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + Color.RESET);
            System.out.println(Color.CYAN + textError + Color.RESET);

            System.out.print("Введите команду (w - вверх, s - вниз, a - влево, d - вправо): ");
            String command = scanner.nextLine();

            // Обработка команд игрока
            switch (command) {
                case "w" -> movePlayer(-1, 0);
                case "s" -> movePlayer(1, 0);
                case "a" -> movePlayer(0, -1);
                case "d" -> movePlayer(0, 1);
                case "add" -> addItemToInventory("ключ"); // Пример добавления предмета
                case "remove" -> removeItemFromInventory("ключ"); // Пример удаления предмета
                default -> textError = "[ Неверная команда! Используйте wasd для перемещения. ]";
            }


            // Пауза для просмотра перед следующим ходом
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Метод для перемещения игрока
    public void movePlayer(int deltaX, int deltaY) {
        int newX = playerX + deltaX;
        int newY = playerY + deltaY;

        if (map[newX][newY].equals(" ") || map[newX][newY].equals("$")) {
            map[playerX][playerY] = " ";
            playerX = newX;
            playerY = newY;
            map[playerX][playerY] = "@";
            textError = "[ Все в порядке ]";
        } else {
            textError = "[ Нельзя пройти сквозь стену или врага! ]";
        }
    }

    // Добавление предмета в инвентарь
    public void addItemToInventory(String item) {
        inventory.put(item, inventory.getOrDefault(item, 0) + 1);
    }

    // Удаление предмета из инвентаря
    public void removeItemFromInventory(String item) {
        if (inventory.containsKey(item)) {
            int count = inventory.get(item);
            if (count > 1) {
                inventory.put(item, count - 1);
            } else {
                inventory.remove(item);
            }
        }
    }

    // Отображение инвентаря
    public void showInventory() {
        System.out.println("Инвентарь:");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.printf("%s[%d], ", entry.getKey(), entry.getValue());
        }
    }

    public static void main(String[] args) {
        Main game = new Main();
        game.playGame();
    }
}
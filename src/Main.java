import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    _Map __map = new _Map();
    String textError = "[ Все в порядке ]";
    String plot = "Тут описывается короткий сюжет именно в этот момент, несколько строк.";
    int lvlPlayer, expCurrent, expRequired = 50;
    Map<String, Integer> inventory = new HashMap<>(); // Инвентарь

    int playerX = 4;
    int playerY = 4;

    // Основной метод игры
    public void playGame() {
        Scanner scanner = new Scanner(System.in);

        // Главный игровой цикл
        while (true) {
            // Очистка консоли (просто добавим пустые строки)
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }

            __map.printMap();
            __map.printLegend();


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

        if (__map._map[newX][newY].equals(" ") || __map._map[newX][newY].equals("$")) {
            __map._map[playerX][playerY] = " ";
            playerX = newX;
            playerY = newY;
            __map._map[playerX][playerY] = "@";
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
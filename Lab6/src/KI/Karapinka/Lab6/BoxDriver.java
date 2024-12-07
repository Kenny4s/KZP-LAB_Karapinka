package KI.Karapinka.Lab6;

/**
 * Клас BoxDriver для тестування функціональності класу Box.
 */
public class BoxDriver {
    public static void main(String[] args) {
        // Створення коробки для предметів
        Box<Item> box = new Box<>();

        // Створення предметів
        Item item1 = new Item("Book", 1.2);
        Item item2 = new Item("Laptop", 2.5);

        // Додавання предметів у коробку
        box.addItem(item1);
        box.addItem(item2);

        // Виведення всіх предметів
        System.out.println("Предмети у коробці:");
        box.getItems().forEach(System.out::println);

        // Знаходження мінімального елемента
        System.out.println("Мінімальний предмет у коробці:");
        System.out.println(box.findMin());

        // Видалення предмета
        box.removeItem(item1);
        System.out.println("Після видалення предмета:");
        box.getItems().forEach(System.out::println);
    }
}

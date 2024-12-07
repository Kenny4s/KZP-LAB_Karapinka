package KI.Karapinka.Lab6;

import java.util.ArrayList;
import java.util.List;

/**
 * Параметризований клас Box для зберігання предметів.
 *
 * @param <T> тип предмету, який має реалізовувати Comparable
 */
public class Box<T extends Comparable<T>> {
    private List<T> items;

    public Box() {
        items = new ArrayList<>();
    }

    /**
     * Додає предмет до коробки.
     *
     * @param item предмет для додавання
     */
    public void addItem(T item) {
        items.add(item);
    }

    /**
     * Видаляє предмет з коробки.
     *
     * @param item предмет для видалення
     * @return true, якщо предмет був успішно видалений, інакше false
     */
    public boolean removeItem(T item) {
        return items.remove(item);
    }

    /**
     * Повертає список усіх предметів у коробці.
     *
     * @return список предметів
     */
    public List<T> getItems() {
        return new ArrayList<>(items);
    }

    /**
     * Знаходить мінімальний елемент у коробці.
     *
     * @return мінімальний елемент, якщо список не порожній, інакше null
     */
    public T findMin() {
        if (items.isEmpty()) {
            return null;
        }

        T min = items.get(0);
        for (T item : items) {
            if (item.compareTo(min) < 0) {
                min = item;
            }
        }
        return min;
    }
}

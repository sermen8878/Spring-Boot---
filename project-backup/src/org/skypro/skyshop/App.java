package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.*;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.Searchable;

import java.util.TreeSet;

public class App {
    public static void main(String[] args) {
        System.out.println("=== ДОМАШНЯЯ РАБОТА 8: STREAM API ===");
        System.out.println();

        // Создание различных продуктов
        SimpleProduct laptop = new SimpleProduct("Ноутбук", 50000);
        SimpleProduct mouse = new SimpleProduct("Мышь", 1500);
        DiscountedProduct phone = new DiscountedProduct("Телефон", 30000, 10);
        FixPriceProduct headphones = new FixPriceProduct("Наушники");

        // Демонстрация работы корзины с Stream API
        demonstrateBasketWithStreams(laptop, mouse, phone, headphones);

        System.out.println("\n" + "=".repeat(50) + "\n");

        // Демонстрация работы поискового движка с Stream API
        demonstrateSearchWithStreams(laptop, phone, headphones);
    }

    /**
     * Демонстрация работы корзины с использованием Stream API
     */
    private static void demonstrateBasketWithStreams(SimpleProduct laptop, SimpleProduct mouse,
                                                     DiscountedProduct phone, FixPriceProduct headphones) {
        System.out.println("=== ДЕМОНСТРАЦИЯ КОРЗИНЫ С STREAM API ===");

        ProductBasket basket = new ProductBasket();

        // Добавление продуктов в корзину
        System.out.println("\n1. Добавление продуктов в корзину:");
        basket.addProduct(laptop);
        basket.addProduct(mouse);
        basket.addProduct(phone);
        basket.addProduct(headphones);
        basket.addProduct(mouse); // Добавляем еще одну мышь

        // Печать содержимого корзины (использует Stream API)
        System.out.println("\n2. Содержимое корзины:");
        basket.printBasket();

        // Проверка наличия продуктов (использует Stream API)
        System.out.println("\n3. Проверка наличия продуктов:");
        System.out.println("Есть ли ноутбук в корзине: " + basket.containsProduct("Ноутбук"));
        System.out.println("Есть ли планшет в корзине: " + basket.containsProduct("Планшет"));

        // Статистика корзины
        System.out.println("\n4. Статистика корзины:");
        System.out.println("Уникальных продуктов: " + basket.getUniqueProductCount());
        System.out.println("Общее количество товаров: " + basket.getTotalProductCount());
        System.out.println("Общая стоимость: " + basket.getTotalPrice() + " руб.");

        // Удаление продукта по имени (использует Stream API)
        System.out.println("\n5. Удаление мыши:");
        basket.removeProductsByName("Мышь");

        // Печать обновленного содержимого
        System.out.println("\n6. Обновленное содержимое корзины:");
        basket.printBasket();

        // Демонстрация очистки корзины
        System.out.println("\n7. Очистка корзины:");
        basket.clearBasket();
        basket.printBasket();
    }

    /**
     * Демонстрация работы поискового движка с использованием Stream API
     */
    private static void demonstrateSearchWithStreams(SimpleProduct laptop, DiscountedProduct phone,
                                                     FixPriceProduct headphones) {
        System.out.println("=== ДЕМОНСТРАЦИЯ ПОИСКА С STREAM API ===");

        SearchEngine searchEngine = new SearchEngine();

        // Добавление продуктов в поисковый движок
        searchEngine.add(laptop);
        searchEngine.add(phone);
        searchEngine.add(headphones);

        System.out.println("В поисковый движок добавлено объектов: " + searchEngine.size());

        // Поиск по разным запросам (использует Stream API)
        System.out.println("\n1. Поиск по запросу 'Ноутбук':");
        TreeSet<Searchable> results1 = searchEngine.search("Ноутбук");
        results1.forEach(result ->
                System.out.println(" - " + result.getStringRepresentation())
        );

        System.out.println("\n2. Поиск по запросу 'Телефон':");
        TreeSet<Searchable> results2 = searchEngine.search("Телефон");
        results2.forEach(result ->
                System.out.println(" - " + result.getStringRepresentation())
        );

        System.out.println("\n3. Поиск по запросу 'Наушники':");
        TreeSet<Searchable> results3 = searchEngine.search("Наушники");
        results3.forEach(result ->
                System.out.println(" - " + result.getStringRepresentation())
        );

        // Поиск по несуществующему запросу
        System.out.println("\n4. Поиск по несуществующему запросу 'Планшет':");
        TreeSet<Searchable> results4 = searchEngine.search("Планшет");
        if (results4.isEmpty()) {
            System.out.println("Результаты не найдены");
        }
    }
}
package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;
import java.util.*;
import java.util.stream.Collectors;

public class ProductBasket {
    private final Map<String, List<Product>> products;

    public ProductBasket() {
        this.products = new HashMap<>();
    }

    /**
     * Добавляет продукт в корзину
     */
    public void addProduct(Product product) {
        products.compute(product.getName(), (name, productList) -> {
            if (productList == null) {
                productList = new ArrayList<>();
            }
            productList.add(product);
            return productList;
        });
    }

    /**
     * Вычисляет общую стоимость корзины с использованием Stream API
     */
    public int getTotalPrice() {
        return products.values().stream()
                .flatMap(List::stream)
                .mapToInt(Product::getPrice)
                .sum();
    }

    /**
     * Печатает содержимое корзины с использованием Stream API
     */
    public void printBasket() {
        if (products.isEmpty()) {
            System.out.println("в корзине пусто");
            return;
        }

        // Вывод каждого продукта с использованием Stream API
        products.values().stream()
                .flatMap(List::stream)
                .forEach(product -> System.out.println(product.toString()));

        // Подсчет специальных товаров
        long specialCount = getSpecialCount();

        System.out.println("Итого: " + getTotalPrice());
        System.out.println("Специальных товаров: " + specialCount);
    }

    /**
     * Проверяет наличие продукта по имени с использованием Stream API
     */
    public boolean containsProduct(String name) {
        return products.values().stream()
                .flatMap(List::stream)
                .anyMatch(product -> product.getName().equals(name));
    }

    /**
     * Удаляет продукты по имени с использованием Stream API
     */
    public List<Product> removeProductsByName(String name) {
        List<Product> removedProducts = products.values().stream()
                .flatMap(List::stream)
                .filter(product -> product.getName().equals(name))
                .collect(Collectors.toList());

        // Удаляем из мапы
        products.remove(name);

        return removedProducts;
    }

    /**
     * Очищает корзину
     */
    public void clearBasket() {
        products.clear();
    }

    /**
     * Приватный метод для подсчета специальных товаров с использованием Stream API
     */
    private long getSpecialCount() {
        return products.values().stream()
                .flatMap(List::stream)
                .filter(Product::isSpecial)
                .count();
    }

    /**
     * Получает количество уникальных продуктов в корзине
     */
    public int getUniqueProductCount() {
        return products.size();
    }

    /**
     * Получает общее количество товаров в корзине
     */
    public int getTotalProductCount() {
        return products.values().stream()
                .mapToInt(List::size)
                .sum();
    }
}
package org.skypro.skyshop.service;

import org.skypro.skyshop.model.product.*;
import org.skypro.skyshop.model.article.Article;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StorageService {
    private final Map<UUID, Product> availableProducts;
    private final Map<UUID, Article> availableArticles;

    public StorageService() {
        this.availableProducts = new HashMap<>();
        this.availableArticles = new HashMap<>();
        initializeTestData();
    }

    private void initializeTestData() {
        // Создаем тестовые продукты
        Product laptop = new SimpleProduct("Ноутбук Lenovo", 50000);
        Product mouse = new SimpleProduct("Компьютерная мышь", 1500);
        Product smartphone = new DiscountedProduct("Смартфон Samsung", 30000, 15);
        Product headphones = new FixPriceProduct("Беспроводные наушники");
        Product tablet = new DiscountedProduct("Планшет Apple", 45000, 10);

        // Создаем тестовые статьи
        Article laptopReview = new Article(
                "Обзор ноутбуков 2024",
                "Ноутбуки стали мощнее и доступнее. Новые процессоры позволяют..."
        );
        Article smartphoneGuide = new Article(
                "Как выбрать смартфон",
                "При выборе смартфона обратите внимание на процессор, память и камеру..."
        );
        Article headphonesArticle = new Article(
                "Топ беспроводных наушников",
                "Беспроводные наушники становятся все популярнее. Рассмотрим лучшие модели..."
        );

        // Добавляем продукты в хранилище
        addProduct(laptop);
        addProduct(mouse);
        addProduct(smartphone);
        addProduct(headphones);
        addProduct(tablet);

        // Добавляем статьи в хранилище
        addArticle(laptopReview);
        addArticle(smartphoneGuide);
        addArticle(headphonesArticle);
    }

    private void addProduct(Product product) {
        availableProducts.put(product.getId(), product);
    }

    private void addArticle(Article article) {
        availableArticles.put(article.getId(), article);
    }

    public Collection<Product> getAllProducts() {
        return Collections.unmodifiableCollection(availableProducts.values());
    }

    public Collection<Article> getAllArticles() {
        return Collections.unmodifiableCollection(availableArticles.values());
    }

    public Collection<Searchable> getAllSearchables() {
        List<Searchable> searchables = new ArrayList<>();
        searchables.addAll(availableProducts.values());
        searchables.addAll(availableArticles.values());
        return Collections.unmodifiableCollection(searchables);
    }

    public Optional<Product> getProductById(UUID id) {
        return Optional.ofNullable(availableProducts.get(id));
    }

    public Optional<Article> getArticleById(UUID id) {
        return Optional.ofNullable(availableArticles.get(id));
    }
}
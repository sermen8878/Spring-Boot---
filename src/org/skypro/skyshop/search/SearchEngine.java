package org.skypro.skyshop.search;

import java.util.*;
import java.util.stream.Collectors;

public class SearchEngine {
    private final Set<Searchable> searchables;
    private final Comparator<Searchable> comparator;

    public SearchEngine() {
        this.searchables = new HashSet<>();
        this.comparator = (item1, item2) -> {
            // Сравнение по длине имени (от большего к меньшему)
            int lengthCompare = Integer.compare(
                    item2.getName().length(),
                    item1.getName().length()
            );
            if (lengthCompare != 0) {
                return lengthCompare;
            }
            // Если длины равны - натуральное сравнение
            return item1.getName().compareTo(item2.getName());
        };
    }

    /**
     * Добавляет Searchable объект в поисковый движок
     */
    public void add(Searchable searchable) {
        searchables.add(searchable);
    }

    /**
     * Поиск объектов по тексту с использованием Stream API
     * Возвращает отсортированный TreeSet с результатами
     */
    public TreeSet<Searchable> search(String searchText) {
        return searchables.stream()
                .filter(item ->
                        item.getSearchTerm()
                                .toLowerCase()
                                .contains(searchText.toLowerCase())
                )
                .collect(Collectors.toCollection(
                        () -> new TreeSet<>(comparator)
                ));
    }

    /**
     * Получить количество объектов в поисковом движке
     */
    public int size() {
        return searchables.size();
    }
}
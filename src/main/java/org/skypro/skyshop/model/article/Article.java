package org.skypro.skyshop.model.article;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

public class Article implements Searchable {
    private final UUID id;
    private final String title;
    private final String text;

    public Article(String title, String text) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.text = text;
    }

    @Override
    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    @Override
    public String getName() {
        return title;
    }

    @JsonIgnore
    @Override
    public String getSearchTerm() {
        return toString();
    }

    @JsonIgnore
    @Override
    public String getContentType() {
        return "ARTICLE";
    }

    @Override
    public String toString() {
        return title + "\n" + text;
    }
}
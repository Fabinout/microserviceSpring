package com.example.domain;

public class Product {

    static long nb = 0;

    public long id;
    public String name, description;

    public Product(String name, String description) {
        this.name = name;
        this.description = description;
        id = nb++;
    }
}

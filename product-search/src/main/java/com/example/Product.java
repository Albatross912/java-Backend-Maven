package com.example;


public class Product {
    private final String name;
    private final String color;
    private final String gender;
    private final String size;
    private final int price;
    private final double rating;

    public Product(String name, String color, String gender, String size, int price, double rating) {
        this.name = name;
        this.color = color;
        this.gender = gender;
        this.size = size;
        this.price = price;
        this.rating = rating;
    }

    // getters and setters
    public String getName() {
        return this.name;
    }
    public double getPrice() {
        return this.price;
    }
    public int getRating(){return (int) this.rating;}
    public String getSize(){return this.size;}
    public String getGender(){return this.gender;}
    public String getColor(){return this.color;}

}

package com.map524.product;

public class Product {
    String name;
    int price;
    int quantity;

    public Product() {
        this.name= "This is a test obj";
        this.price=0;
        this.quantity=0;
    }

    public Product(String name, int price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Boolean inStock(int quantitySelected){
        return this.getQuantity() >= quantitySelected;
    }
}

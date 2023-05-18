package org.example.KursachP.models;

import jakarta.persistence.*;

@Entity
@Table (name = "product")
public class Product {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "productname")
    private String productName;
    @Column(name = "color")
    private String color;
    @Column(name = "size")
    private int size;
    @Column(name = "amount")
    private int amount;
    @Column(name = "price")
    private int price;

    public Product() {
    }

    public Product(int id, String productName, String color, int size, int amount, int price) {
        this.id = id;
        this.productName = productName;
        this.color = color;
        this.size = size;
        this.amount = amount;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

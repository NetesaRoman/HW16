package models;

import java.time.LocalDate;

public class Product {
    private String type;
    private double price;
    private boolean sale;
    private LocalDate date;

    public Product(String type, double price, boolean sale) {
        this.type = type;
        this.price = price;
        this.sale = sale;
        this.date = LocalDate.now();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isSale() {
        return sale;
    }

    public void setSale(boolean sale) {
        this.sale = sale;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void printInfo() {
        double realPrice = price;
        if (sale) realPrice = price - (price / 10);

        System.out.println("Тип продукта: " + type + " Цена: " + realPrice + " Дата: " + date.toString());
    }
}

package com.step;
import java.time.LocalDate;

public class Product {
    
    String name;
    double purchasePrice;
    double sellingPrice;
    LocalDate expirationDate;
    String description;
    int amountProduct;    

    public Product(String name, double purchasePrice, double sellingPrice, LocalDate expirationDate, String description, int amountProduct) {
        this.name = name;
        this.purchasePrice = purchasePrice;
        this.sellingPrice = sellingPrice;
        this.expirationDate = expirationDate;
        this.description = description;
        this.amountProduct = amountProduct;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getAmountProduct() {
        return amountProduct;
    }

    public void setAmountProduct(int amountProduct) {
        this.amountProduct = amountProduct;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public String getDescription() {
        return description;
    }

    
}

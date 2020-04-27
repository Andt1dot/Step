/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.step;

import java.time.LocalDate;

/**
 *
 * @author Ant1dot
 */
public class Product {
    
    String name;
    double purchasePrice;
    double sellingPrice;
    LocalDate expirationDate;
    String description;    

    public Product(String name, double purchasePrice, double sellingPrice, LocalDate expirationDate, String description) {
        this.name = name;
        this.purchasePrice = purchasePrice;
        this.sellingPrice = sellingPrice;
        this.expirationDate = expirationDate;
        this.description = description;
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

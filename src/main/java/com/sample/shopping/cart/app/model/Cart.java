package com.sample.shopping.cart.app.model;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Cart {

    private List<Product> products;
    private double totalPrice;

    public Cart(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Cart() {
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}

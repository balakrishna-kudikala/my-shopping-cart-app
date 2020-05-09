package com.sample.shopping.cart.app.model;

import org.springframework.stereotype.Component;

@Component
public class Slab {

    private int number;
    private int from;
    private int to;
    private double discount;

    public Slab(int number, int from, int to, double discount) {
        this.number = number;
        this.from = from;
        this.to = to;
        this.discount = discount;
    }

    public Slab() {
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}

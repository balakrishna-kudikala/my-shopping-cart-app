package com.sample.shopping.cart.app.model;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Customer {

    private String id;
    private String name;
    private String type;
    private Cart cart;
    private List<Slab> slabs;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public List<Slab> getSlabs() {
        return slabs;
    }

    public void setSlabs(List<Slab> slabs) {
        this.slabs = slabs;
    }
}

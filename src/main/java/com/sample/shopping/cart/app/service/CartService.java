package com.sample.shopping.cart.app.service;

import com.sample.shopping.cart.app.model.Cart;
import com.sample.shopping.cart.app.model.Slab;

import java.util.List;

public interface CartService {

    public double getFinalPrice(Cart cart, String customerType) throws Exception;
}

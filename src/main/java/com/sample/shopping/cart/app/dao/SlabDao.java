package com.sample.shopping.cart.app.dao;

import com.sample.shopping.cart.app.model.Slab;

import java.util.List;

public interface SlabDao {

    public List<Slab> getSlabs(String customerType);
}

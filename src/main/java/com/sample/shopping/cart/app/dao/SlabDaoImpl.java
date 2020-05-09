package com.sample.shopping.cart.app.dao;

import com.sample.shopping.cart.app.constants.CustomerType;
import com.sample.shopping.cart.app.model.Slab;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("slabDao")
public class SlabDaoImpl implements SlabDao {

    private static Map<String, List<Slab>> slabsMap = new HashMap<>();
    private static List<Slab> regularSlabs = new ArrayList<>();
    private static List<Slab> premiumSlabs = new ArrayList<>();

    // Ideally, these slabs dynamic and should have come from a database. I stubbed them so that not to deal with database calls.
    static {
        slabsMap.put(CustomerType.REGULAR.name(), regularSlabs);
        slabsMap.put(CustomerType.PREMIUM.name(), premiumSlabs);

        regularSlabs.add(new Slab(1, 0, 5000, 0));
        regularSlabs.add(new Slab(2, 5000, 10000, 10));
        regularSlabs.add(new Slab(3, 10000, Integer.MAX_VALUE, 20));

        premiumSlabs.add(new Slab(1, 0, 4000, 10));
        premiumSlabs.add(new Slab(2, 4000, 8000, 15));
        premiumSlabs.add(new Slab(3, 8000, 12000, 20));
        premiumSlabs.add(new Slab(4, 12000, Integer.MAX_VALUE, 30));
    }
    @Override
    public List<Slab> getSlabs(String customerType) {
        return slabsMap.get(customerType);
    }
}

package com.sample.shopping.cart.app.service;

import com.sample.shopping.cart.app.constants.CustomerType;
import com.sample.shopping.cart.app.constants.Errors;
import com.sample.shopping.cart.app.dao.SlabDao;
import com.sample.shopping.cart.app.model.Cart;
import com.sample.shopping.cart.app.model.Slab;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.function.BiFunction;

@Service("cartService")
public class CartServiceImpl implements CartService {

    @Autowired
    private SlabDao slabDao;

    private BiFunction<Slab, Double, Double> discountCalculator = (slab, amount) -> {
        if (slab == null || amount == 0 || slab.getDiscount()==0 ||slab.getTo()==0){
            return 0d;
        }
        double amountToApply = Math.min(amount, slab.getTo()-slab.getFrom());
        return amountToApply*slab.getDiscount()/100;
    };

    @Override
    public double getFinalPrice(Cart cart, String customerType) throws Exception {
        if (!CustomerType.REGULAR.name().equalsIgnoreCase(customerType) && !CustomerType.PREMIUM.name().equalsIgnoreCase(customerType)){
            throw new Exception(Errors.INVALID_CUSTOMER_TYPE.getMessage());
        }
        if (cart == null || cart.getTotalPrice() < 0) {
            throw new Exception(Errors.INVALID_CART.getMessage());
        }
        List<Slab> slabs = slabDao.getSlabs(customerType);
        double amount = cart.getTotalPrice();
        double discountedPrice = 0d;
        if (CollectionUtils.isEmpty(slabs)){
            return amount;
        } else {
            for (Slab slab: slabs){
                discountedPrice +=discountCalculator.apply(slab, amount);
                amount -= Math.min(amount, slab.getTo()-slab.getFrom());
            }
        }
        return cart.getTotalPrice() - discountedPrice;
    }
}

package com.sample.shopping.cart.app.constants;

public enum Errors {
    INVALID_CART("Invalid Cart!!"), INVALID_CUSTOMER_TYPE("Invalid Customer Type!!");
    private String message;
    private  Errors(String message){
        this.message=message;
    }
    public String getMessage() {
        return message;
    }
}

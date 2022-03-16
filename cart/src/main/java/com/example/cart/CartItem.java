package com.example.cart;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CartItem {
    @Id
    @GeneratedValue
    private int id;
    private int productId;
    private int quantity;

    public CartItem() {
    }

    public CartItem(int productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
}
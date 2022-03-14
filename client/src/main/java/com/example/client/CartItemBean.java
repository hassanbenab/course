package com.example.client;

public class CartItemBean {
    private Long id;
    private Long productId;
    private Integer quantity;

    public CartItemBean(Long id, Long productId, Integer quantity) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
    }
}
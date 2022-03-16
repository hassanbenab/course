package com.example.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@FeignClient(name = "ms-cart", url = "localhost:8092")
public interface MsCartProxy {
    @PostMapping(value = "/cart")
    public ResponseEntity<CartBean> createNewCart(@RequestBody CartBean cartData);
    @GetMapping(value = "/cart/{id}")
    public Optional<CartBean> getCart(@PathVariable int id);
    @PostMapping(value = "/add-cart/{id}")
    public ResponseEntity<CartItemBean> addProductToCart(@PathVariable int id, @RequestParam int itemId , @RequestParam int quantity);
}
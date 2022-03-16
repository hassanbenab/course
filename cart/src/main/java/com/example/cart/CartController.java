package com.example.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.Optional;

@RestController
public class CartController {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    CartItemRepository cartItemRepository;

    @PostMapping(value = "/cart")
    public ResponseEntity<Cart> createNewCart(@RequestBody Cart cartData)
    {
        Cart cart = cartRepository.save(new Cart());
        if (cart == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Couldn't create a new cart");
        return new ResponseEntity<Cart>(cart, HttpStatus.CREATED);
    }


    @GetMapping(value = "/cart/{id}")
    public Optional<Cart> getCart(@PathVariable Long id)
    {
        Optional<Cart> cart = cartRepository.findById(id);
        if (!cart.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Couldn't get cart");
        return cart;
    }

    @PostMapping(value = "/add-cart/{id}")
    public ResponseEntity<CartItem> addProductToCart(@PathVariable Long id, @RequestParam int ItemId, @RequestParam int quantity)
    {
        CartItem cartItem = new CartItem(ItemId,quantity);
        Cart cart = cartRepository.findById(id).stream().findFirst().orElse(null);
        if (cart == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Couldn't get cart");
        cart.addProduct(cartItem);
        cartRepository.save(cart);
        return new ResponseEntity<CartItem>(cartItem, HttpStatus.CREATED);
    }
}

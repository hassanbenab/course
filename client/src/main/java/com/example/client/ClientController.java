package com.example.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ClientController {
    @Autowired
    private MsProductProxy msProductProxy;
    private MsCartProxy msCartProxy;
    @RequestMapping("/")
    public String index(Model model) {
        List<ProductBean> products = msProductProxy.list();
        model.addAttribute("products", products);
        return "index";
    }

    @RequestMapping("/product-detail/{id}")
    public String detail(Model model, @PathVariable Long id) {
        ProductBean product = msProductProxy.get(id).stream().findFirst().orElse(null);
        model.addAttribute("product", product);
        return "detail";
    }

    @PostMapping("/add-product/{cartId}")
    public String addItemToCart(@PathVariable Long cartId, @RequestParam Long itemId, @RequestParam int quantity){
        System.out.println(itemId);
        msCartProxy.addProductToCart(cartId,itemId,quantity);
        return "index";
    }
}
package com.example.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "ms-product", url = "localhost:8091")
public interface MsProductProxy {
    @GetMapping(value = "/products")
    public List<ProductBean> list();
    @GetMapping(value = "/product/{id}")
    public Optional<ProductBean> get(@PathVariable Long id);
}

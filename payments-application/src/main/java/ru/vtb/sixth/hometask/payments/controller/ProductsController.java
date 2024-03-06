package ru.vtb.sixth.hometask.payments.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.vtb.sixth.hometask.entity.ClientProductEntity;
import ru.vtb.sixth.hometask.payments.service.ProductsService;

import java.util.List;

@RestController
public class ProductsController {

    private final ProductsService productsService;

    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping("/product/user")
    // localhost:8080/product/user?userid=3
    public ResponseEntity<List<ClientProductEntity>> getClientProductsByUserId(@RequestParam Long userid) throws JsonProcessingException {
        List<ClientProductEntity> result = productsService.getClientProductsByUserId(userid);
        return ResponseEntity.ok().body(result);
    }
}

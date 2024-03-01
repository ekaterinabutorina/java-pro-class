package ru.vtb.fifth.hometask.controller;

import ru.vtb.fifth.hometask.entity.ClientProductEntity;
import ru.vtb.fifth.hometask.service.ClientProductsService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientProductController {

    private final ClientProductsService clientProductsService;

    public ClientProductController(ClientProductsService clientProductsService) {
        this.clientProductsService = clientProductsService;
    }

    @GetMapping("/create/table")
    public void createClientProductsTable() {
        clientProductsService.createClientProductsTable();
    }

    @PostMapping("/product")
    public ResponseEntity<ClientProductEntity> addClientProduct(@RequestBody ClientProductEntity request) {
        var result = clientProductsService.addClientProduct(request.getId(), request.getAccount(), request.getBalance(), request.getType(), request.getUserId());
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/product")
    // localhost:8080/product?id=1
    public ResponseEntity<ClientProductEntity> getClientProductByProductId(@RequestParam Long id) {
        var result = clientProductsService.getClientProductByProductId(id);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/product/user")
    // localhost:8080/product/user?userid=3
    public ResponseEntity<List<ClientProductEntity>> getClientProductsByUserId(@RequestParam Long userid) {
        var result = clientProductsService.getClientProductsByUserId(userid);
        return ResponseEntity.ok().body(result);
    }
}

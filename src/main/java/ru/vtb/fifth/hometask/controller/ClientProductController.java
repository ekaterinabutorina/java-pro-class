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

    private final ClientProductsService service;

    public ClientProductController(ClientProductsService service) {
        this.service = service;
    }

    @GetMapping("create/table")
    public void createClientProductsTable() {
        service.createClientProductsTable();
    }

    @PostMapping("clientproducts/create")
    public ClientProductEntity addClientProduct(@RequestBody ClientProductEntity request) {
        return service.addClientProduct(request.getId(), request.getAccount(), request.getBalance(), request.getType(), request.getUserId());
    }

    @GetMapping("clientproducts/getbyid")
    public ResponseEntity<ClientProductEntity> getClientProductByProductId(@RequestParam Long id) {
        return service.getClientProductByProductId(id);
    }

    @GetMapping("clientproducts/getbyproductid")
    public ResponseEntity<List<ClientProductEntity>> getClientProductsByUserId(@RequestParam Long userid) {
        return service.getClientProductsByUserId(userid);
    }
}

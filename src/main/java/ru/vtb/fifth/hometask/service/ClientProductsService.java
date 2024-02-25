package ru.vtb.fifth.hometask.service;

import ru.vtb.fifth.hometask.entity.ClientProductEntity;
import ru.vtb.fifth.hometask.repository.ClientProductsRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientProductsService {

    private static final Logger logger = LoggerFactory.getLogger(ClientProductsService.class.getName());

    private final ClientProductsRepository repository;

    public ClientProductsService(ClientProductsRepository repository) {
        this.repository = repository;
    }

    public void createClientProductsTable() {
        repository.createClientProductsTable();
        logger.info("Client products table was created.");
    }

    public ClientProductEntity addClientProduct(Long id, String account, String balance, String type, Long userId) {
        repository.createClientProduct(id, account, balance, type, userId);
        logger.info("Client product id = " + id + " was created.");
        return new ClientProductEntity(id, account, balance, type, userId);
    }

    public ResponseEntity<ClientProductEntity> getClientProductByProductId(Long id) {
        var result = repository.getClientProductByProductId(id);
        return result == null ? ResponseEntity.notFound().build() : ResponseEntity.ok().body(result);
    }

    public ResponseEntity<List<ClientProductEntity>> getClientProductsByUserId(Long userId) {
        var result = repository.getClientProductsByUserId(userId);
        return result.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok().body(result);
    }
}

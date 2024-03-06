package ru.vtb.sixth.hometask.payments.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.vtb.sixth.hometask.entity.ClientProductEntity;

import java.util.Arrays;
import java.util.List;

@Service
public class ProductsService {

    @Autowired
    RestTemplate restTemplate;

    private final ObjectMapper objectMapper;

    public ProductsService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<ClientProductEntity> getClientProductsByUserId(Long userId) throws JsonProcessingException {
        var result = restTemplate.getForObject("http://localhost:8080/product/user?userid="+ userId, String.class);
        return Arrays.stream(objectMapper.readValue(result, ClientProductEntity[].class)).toList();
    }
}

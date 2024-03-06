package ru.vtb.sixth.hometask.repository;

import ru.vtb.sixth.hometask.entity.ClientProductEntity;
import ru.vtb.sixth.hometask.repository.constants.SQL_CONSTANTS;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClientProductsRepository {

    private final JdbcTemplate jdbcTemplate;

    public ClientProductsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createClientProductsTable() {
        jdbcTemplate.execute(SQL_CONSTANTS.CREATE_CLIENT_PRODUCTS_TABLE);
    }

    public ClientProductEntity getClientProductByProductId(Long id) {
        ClientProductEntity result;
        try {
            result = jdbcTemplate.queryForObject(SQL_CONSTANTS.SELECT_CLIENT_PRODUCT_BY_ID, clientProductRowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
        return result;
    }

    public List<ClientProductEntity> getClientProductsByUserId(Long userId) {
        return jdbcTemplate.query(SQL_CONSTANTS.SELECT_CLIENT_PRODUCTS_BY_USERID, clientProductRowMapper, userId);
    }

    public void createClientProduct(Long id, String account, String balance, String type, Long userId) {
        jdbcTemplate.update(SQL_CONSTANTS.INSERT_CLIENT_PRODUCTS, id, account, balance, type, userId);
    }

    private final RowMapper<ClientProductEntity> clientProductRowMapper = (resultSet, rowNum) -> {
        ClientProductEntity clientProduct = new ClientProductEntity();
        clientProduct.setId(resultSet.getLong("id"));
        clientProduct.setAccount(resultSet.getString("account"));
        clientProduct.setBalance(resultSet.getString("balance"));
        clientProduct.setType(resultSet.getString("type"));
        clientProduct.setUserId(resultSet.getLong("userid"));
        return clientProduct;
    };
}

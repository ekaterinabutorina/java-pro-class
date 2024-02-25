package ru.vtb.fifth.hometask.repository;

import ru.vtb.fifth.hometask.entity.ClientProductEntity;

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
        jdbcTemplate.execute(
                "CREATE TABLE IF NOT EXISTS client_products (ID BIGSERIAL PRIMARY KEY, ACCOUNT VARCHAR(255), BALANCE VARCHAR(255), TYPE VARCHAR(255), USERID BIGSERIAL)");
    }

    public ClientProductEntity getClientProductByProductId(Long id) {
        ClientProductEntity result;
        try {
            result = jdbcTemplate.queryForObject("SELECT * FROM client_products WHERE id = ?", clientProductRowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
        return result;
    }

    public List<ClientProductEntity> getClientProductsByUserId(Long userId) {
        return jdbcTemplate.query("SELECT * FROM client_products WHERE userid = ?", clientProductRowMapper, userId);
    }

    public void createClientProduct(Long id, String account, String balance, String type, Long userId) {
        jdbcTemplate.update(
                "INSERT INTO client_products (id, account, balance, type, userid) VALUES (?, ?, ?, ?, ?) ON CONFLICT DO NOTHING",
                id, account, balance, type, userId);
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

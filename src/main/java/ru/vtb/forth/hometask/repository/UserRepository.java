package ru.vtb.forth.hometask.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createUsersTable() {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS users (ID BIGSERIAL PRIMARY KEY, USERNAME VARCHAR(255) UNIQUE)");
    }

    public void deleteUser(Long userId) {
        jdbcTemplate.update("DELETE FROM users WHERE id = ?", userId);
    }

    public String getById(Long id) {
        return jdbcTemplate.queryForObject("SELECT username FROM users WHERE id = ?", String.class, id);
    }

    public void createUser(Long id, String userName) {
        jdbcTemplate.update("INSERT INTO users (id, username) VALUES (?, ?) ON CONFLICT DO NOTHING", id, userName);
    }

    public List<String> findAll() {
        return jdbcTemplate.queryForList("SELECT username FROM users", String.class);
    }
}

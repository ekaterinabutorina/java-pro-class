package ru.vtb.fifth.hometask.repository.constants;

public class SQL_CONSTANTS {

    public static final String CREATE_CLIENT_PRODUCTS_TABLE =
            "CREATE TABLE IF NOT EXISTS client_products (ID BIGSERIAL PRIMARY KEY, ACCOUNT VARCHAR(255), BALANCE VARCHAR(255), TYPE VARCHAR(255), USERID BIGSERIAL)";

    public static final String SELECT_CLIENT_PRODUCT_BY_ID = "SELECT * FROM client_products WHERE id = ?";

    public static final String SELECT_CLIENT_PRODUCTS_BY_USERID = "SELECT * FROM client_products WHERE userid = ?";

    public static final String INSERT_CLIENT_PRODUCTS =
            "INSERT INTO client_products (id, account, balance, type, userid) VALUES (?, ?, ?, ?, ?) ON CONFLICT DO NOTHING";

}

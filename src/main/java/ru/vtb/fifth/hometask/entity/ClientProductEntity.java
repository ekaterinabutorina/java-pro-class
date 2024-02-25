package ru.vtb.fifth.hometask.entity;

public class ClientProductEntity {

    private Long id;

    private String account;

    private String balance;

    private String type;

    private Long userId;

    public ClientProductEntity() {
    }

    public ClientProductEntity(Long id, String account, String balance, String type, Long userId) {
        this.id = id;
        this.account = account;
        this.balance = balance;
        this.type = type;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}

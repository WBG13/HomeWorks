package com.firstshop.Commands;

public enum Command {
    CREATE_USER("INSERT INTO users "),
    CREATE_PRODUCT("INSERT INTO products "),
    READ_USER("SELECT * FROM users "),
    READ_PRODUCTS("SELECT * FROM products "),
    UPDATE_USER("UPDATE users"),
    UPDATE_PRODUCT("UPDATE products"),
    DELETE_USER("DELETE FROM users"),
    DELETE_PRODUCT("DELETE FROM products");

    public String command;

    Command(String command) {
        this.command = command;
    }

    public String toString() {
        return command;
    }
}

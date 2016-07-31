package com.firstshop.Commands;

public enum Table {

    //List of product tables
    product_id("Product id"),
    product_name("Product name"),
    product_image("Product image"),
    product_amount("Product amount"),
    product_price("Product price"),
    product_discription("Product discription"),

    //List of user tables
    user_id("User id"),
    user_name("User name"),
    user_pass("User password"),
    user_status("User status"),
    user_address("User address");

    String table;
    Table(String table){
        this.table = table;
    }

    @Override
    public String toString(){
        return this.table;
    }
}

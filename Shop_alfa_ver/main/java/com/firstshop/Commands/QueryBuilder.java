package com.firstshop.Commands;

import com.firstshop.Containers.ProductContainer;
import com.firstshop.Containers.RequestResponseCard;
import com.firstshop.Containers.UserContainer;
import com.firstshop.dbhelper.DBManager;
import com.firstshop.dbhelper.MySQLServerConnectionBehavior;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryBuilder {
    DBManager dbm = new DBManager();
    StringBuilder sb = new StringBuilder("<html><body>");
    String category;

    //Create table form using ENUM and MYSQL query
    public enum cat {
        USERS("users "), PRODUCTS("products ");
        String category = null;

        cat(String category) {
            this.category = category;
        }

        @Override
        public String toString() {
            return this.category;
        }
    }

    @Deprecated
    //required rebuild for Request Response Card
    public String buildUsersTable(Table [] tables, String crudQuery){
        return buildATable(tables, crudQuery.concat(cat.USERS.toString()));}

    @Deprecated
    //required rebuild for Request Response Card
    public String buildProductsTable(Table [] tables, String crudQuery){
        return buildATable(tables, crudQuery.concat(cat.PRODUCTS.toString()));}


    // Low level cmd

    @Deprecated
    //required rebuild for Request Response Card
    public String buildTableOfUsers(Table[] table, String query){
        return buildATable(table, query.concat("users "));
    }
    @Deprecated
    //required rebuild for Request Response Card
    public String buildTableOfProducts(Table[] table, String query){
        return buildATable(table, query.concat("products "));}

    @Deprecated
    //required rebuild for Request Response Card
    public boolean incrementProduct(String productName){
        int amount = getProductAmount(productName)+1;
        return changeProductAmount(amount , productName);
    }
    @Deprecated
    //required rebuild for Request Response Card
    public boolean decrementProduct(String productName){
        int amount = getProductAmount(productName)-1;
        return changeProductAmount(amount , productName);
    }

    @Deprecated
    //required rebuild for Request Response Card
    public boolean deleteUser(String userName){
        return executeLowLevelCommand(userName, userName);
    }

    @Deprecated
    //required rebuild for Request Response Card
    public boolean deleteProduct(String productName){
        String query = "delete from products where Product_Name ='" + productName+ "');";
        return executeLowLevelCommand(query, productName);
    }

    public boolean createUser(RequestResponseCard rrq, UserContainer userContainer){
        rrq.addRequestPart("INSERT INTO users VALUES ('"+userContainer.getName()+"','"+userContainer.getPassword()+"');");
        rrq.setName(userContainer.getName());
        return executeLowLevelCommand(query, userContainer.getName());}

    //TODO Finish methods
    public boolean changeProductDescription(String name, String modificator){
        String query = "";
        return executeLowLevelCommand(query, modificator);
    }
    public boolean changeUserPassword(){return false;}


    public boolean createProduct(ProductContainer productContainer){
        String query = "INSERT INTO products " + "VALUES ('" +
                productContainer.getName() +            "', '" +
                productContainer.getImageLocation() +   "', '" +
                productContainer.getAmount() +          "', '" +
                productContainer.getPrice() +           "', '" +
                productContainer.getCategory() +        "', '" +"');";
        return executeLowLevelCommand(query, productContainer.getName());
    }

    //Middle level commands

    private String buildATable(Table[] table, String query) {
        try {
            sb.append("<table border=1>" +
                    "<tr>");
            for (int i = 0; i < table.length; i ++){
            sb.append("<td>"+table[i].toString()+"</td>");
            }
            // TODO REMOVE
            if (query.equals("") || query.equals("null")) query = "SELECT * FROM " + category +";";
            //
            dbm.setConnectionBehavior(new MySQLServerConnectionBehavior());
            dbm.openConnection();
            ResultSet rs = dbm.ExecuteQueryResultSet(query);
            sb.append("<tr>");
            while (rs.next()) {
                for (int i = 0; i < table.length; i++){
                sb.append("<td>" + rs.getString(table[i].name()) + "</td>");
                }
            sb.append("</tr>");
            sb.append("</table>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        dbm.closeConnection(true);
        return String.valueOf(sb);
    }


    //todo Request Responde Card
    public boolean checkPassword(String name, String password){
            String exist = "";
            String passwordFromDB = null;
            try {
                dbm.setConnectionBehavior(new MySQLServerConnectionBehavior());
                dbm.openConnection();
                ResultSet rs = dbm.ExecuteQueryResultSet("SELECT * from users where user_name='" + name + ";");
                while (rs.next()) {
                passwordFromDB = rs.getString("user_password");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return BCrypt.checkpw(password, passwordFromDB);
        }

    private boolean executeLowLevelCommand(RequestResponseCard rrq){
        dbm.setConnectionBehavior(new MySQLServerConnectionBehavior());
        dbm.openConnection();
        String correction = null;
        try {
            dbm.ExecuteQueryResultSet(rrq.getRequest());
                ResultSet rs = dbm.ExecuteQueryResultSet(rrq.getRespond());
            while (rs.next()) {
                correction= rs.getString(rrq.getOutputExcludedTargets());
            }
        if (correction == rrq.getOutputExcludedTargets());
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return false;
    }


    private boolean changeProductAmount(int modificator, String productName){
        dbm.setConnectionBehavior(new MySQLServerConnectionBehavior());
        dbm.openConnection();
        boolean executingCommand = false;
        try {
            dbm.ExecuteQueryResultSet(
                    "UPDATE products SET `product_amount`='" + modificator +
                            " 'WHERE `product_name`='" + productName+";");
            executingCommand = (modificator == getProductAmount(productName));
        } catch (Exception e){
            e.printStackTrace();
        }
        dbm.closeConnection(true);
        return executingCommand;
    }


    public int getProductAmount(String productName){
        int amount = 0;
        dbm.setConnectionBehavior(new MySQLServerConnectionBehavior());
        dbm.openConnection();
        try {
            ResultSet rs = dbm.ExecuteQueryResultSet("SELECT * from products where product_name='" + productName + ";");
            while(rs.next()){
                amount = Integer.parseInt(rs.getString("product_amount"));
            }
        }catch (Exception e){e.printStackTrace();}
        dbm.closeConnection(true);
        return amount;
    }


    }


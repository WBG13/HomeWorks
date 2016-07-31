package com.firstshop.dbhelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLServerConnectionBehavior
        extends DBUserInfo
        implements ServerConnectionBehavior {
    public MySQLServerConnectionBehavior() {
        super("***", "***");

    }

    public MySQLServerConnectionBehavior(String uid, String pwd, String cat) {
        super(uid, pwd/*, cat*/);
    }

    @Override
    public Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection con = DriverManager.getConnection(getConnectionURL(), getUserId(), getPassword());
            return con;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getConnectionURL() {
        System.out.println("get Connection = " + ("jdbc:mysql://localhost:3306/database" + getUserId() + getPassword()));
        return String.format("jdbc:mysql://localhost:3306/database");
    }

    @Override
    public String getConnectionDetails() {
        return "MySQL Database Connection to " + getCat();
    }

    @Override
    public String getTablesSchemaQuery() {
        return "select table_name from information_schema.tables " +
                "where table_schema = " + getCat();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        
    }
}

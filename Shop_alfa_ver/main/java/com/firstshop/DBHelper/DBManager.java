package com.firstshop.dbhelper;

import java.io.Serializable;
import java.sql.*;

public class DBManager implements Serializable {
    Connection cn = null;
    ServerConnectionBehavior scb = null;

    public DBManager() {
    }

    public DBManager(ServerConnectionBehavior connectionBehavior) {
        scb = connectionBehavior;
    }

    public boolean setConnectionBehavior(ServerConnectionBehavior value) {
        if (value == null) {
            throw new IllegalArgumentException("Please use a valid connection");
        }
        scb = value;
        return true;
    }

    public boolean openConnection() {
        try {
            if (scb == null) {
                throw new IllegalArgumentException("Define a connection behavior");
            }
            if (cn != null) {
                closeConnection(false);
            }
            cn = scb.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        if (cn == null) return false;
        return true;
    }

    public boolean closeConnection(boolean keepAlive) {
        try {
            if (cn != null) {
                if (!cn.isClosed()) {
                    cn.close();
                }
            }
            if (keepAlive) {
                {
                    cn = null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean isConnected() {
        return cn != null;
    }

    public boolean executeNonQuery(String query) {
        try {
            Statement st = cn.createStatement();
            int i = st.executeUpdate(query);
            //log it
            if (i == -1) {
                return false;
            }
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public ResultSet ExecuteQueryResultSet(String query) throws SQLException {
            System.out.println("***EQR LOG***\n Query = " + query);
//            cn.createStatement();
            PreparedStatement st = cn.prepareStatement(query);
            System.out.println("\n PreparedStatement = Complete");
            ResultSet rs = st.executeQuery();
            System.out.println("\n Result = Complete \n ***End of log***");


            return rs;
    }

    public Connection getConnection() {
        return cn;
    }

    public String getConnectionUrl() {
        return scb.getConnectionURL();
    }

    public String getConnectionSchemeQuery() {
        return scb.getTablesSchemaQuery();
    }
}

package org.example.ServiceDB;

import java.sql.*;

public class Database {
    private static final Database INSTANCE = new Database();
    private Connection connection;
    private Database() {
        String dbUrl = "jdbc:mysql://URL/";
        String dbUser = "name";
        String dbPass = "password";

        try {
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static Database getInstance(){
        return INSTANCE;
    }

    public int executeUpdate(String sql) {
        try (Statement st = connection.createStatement()) {
                return st.executeUpdate(sql);
            } catch (Exception ex) {
                ex.printStackTrace();
                return -1;
            }
    }

    public Connection getConnection() {
        return connection;
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

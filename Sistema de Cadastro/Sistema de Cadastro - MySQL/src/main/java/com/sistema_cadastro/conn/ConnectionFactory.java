package com.sistema_cadastro.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/sistema_cadastros";
        String user = "root";
        String password = "tiago123";

        return DriverManager.getConnection(url, user, password);

    }
}

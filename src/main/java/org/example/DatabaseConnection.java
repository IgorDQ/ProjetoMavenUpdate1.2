

package org.example;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/loja"
                    + "?useSSL=false"
                    + "&serverTimezone=UTC"
                    + "&allowPublicKeyRetrieval=true";

    private static final String USUARIO = "root";
    private static final String SENHA = "J040z!n80";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}

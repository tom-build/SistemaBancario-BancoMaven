package com.banco.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteConnectionTest {

    private static final String URL =
        "jdbc:sqlite:bancoTest.db";

    public static Connection conectar() {

        try {
            return DriverManager
                .getConnection(URL);

        } catch (SQLException e) {

            throw new RuntimeException(
                "Erro ao conectar no banco de teste",
                e
            );
        }
    }
}
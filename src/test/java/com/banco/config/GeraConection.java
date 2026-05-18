package com.banco.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GeraConection {

    private static final String URL = "jdbc:sqlite:bancoTest.db";

    public static Connection conectar() {
        try {
            return DriverManager.getConnection(URL);

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar no banco de teste", e);
        }
    }
}
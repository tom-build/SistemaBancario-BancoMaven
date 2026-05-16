package com.banco.config;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DatabaseTest {
    @Test
    void deveCriarTabelaContas() throws Exception {

        Database.inicializar();
        try (Connection conn = SQLiteConnection.conectar()) {
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT name FROM sqlite_master " + "WHERE type = 'table' AND name = 'contas' ");
            assertTrue(rs.next());
        }
    }
}
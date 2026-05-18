package com.banco.config;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

public class SQLiteConnectionTest {
    
    @Test
    void deveConectarNoBancoDeTeste()
        throws SQLException {

        Connection conn = GeraConection.conectar();

        assertNotNull(conn);

        assertFalse(conn.isClosed());
        }
}

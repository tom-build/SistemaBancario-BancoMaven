package com.banco.service;

import com.banco.config.SQLiteConnectionTest;
import com.banco.dao.ContaDAO;
import com.banco.model.ContaBancaria;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ContaServiceTest {

    private ContaService service;

    @BeforeEach
    void setup() {

        Connection conn = SQLiteConnectionTest.conectar();

        try {
            PreparedStatement create = conn.prepareStatement(
            """
            CREATE TABLE IF NOT EXISTS contas (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nome TEXT NOT NULL,
                saldo REAL NOT NULL,
                tipo TEXT NOT NULL
                )
            """
            );

            create.executeUpdate();
            PreparedStatement delete = conn.prepareStatement("DELETE FROM contas");

            delete.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        ContaDAO dao = new ContaDAO(conn);
        service = new ContaService(dao);
    }

    @Test
    void deveCriarConta() {
        service.criarConta("Maria", 500, "corrente");

        assertTrue(service.ListarContas().stream()
                .anyMatch(c -> c.getNome().equals("maria") && c.getSaldo() == 500 && c.getTipo().equals("corrente")));
    }

    @Test
    void deveListarContas() {
        service.criarConta("João", 1000, "poupanca");
        service.criarConta("Ana", 1500, "corrente");
        service.criarConta("Carlos", 2000, "poupanca");

        List<ContaBancaria> contas = service.ListarContas();
        contas.forEach(System.out::println);

        assertEquals(3, contas.size());
    }
}

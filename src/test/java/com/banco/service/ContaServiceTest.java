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
                            """);

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

        assertTrue(service.listarContas().stream()
                .anyMatch(c -> c.getNome().equals("maria") && c.getSaldo() == 500 && c.getTipo().equals("corrente")));
    }

    @Test
    void deveListarContas() {
        service.criarConta("João", 1000, "poupanca");
        service.criarConta("Ana", 1500, "corrente");
        service.criarConta("Carlos", 2000, "poupanca");

        List<ContaBancaria> contas = service.listarContas();
        contas.forEach(System.out::println);

        assertEquals(3, contas.size());
    }

    @Test
    void deveListarPorId() {
        service.criarConta("João", 1000, "poupanca");

        ContaBancaria conta = service.listarContas().get(0);
        ContaBancaria contaEncontrada = service.buscarPorId(conta.getId());

        assertNotNull(contaEncontrada);
        assertEquals(conta.getNome(), contaEncontrada.getNome());
        assertEquals(conta.getSaldo(), contaEncontrada.getSaldo());
        assertEquals(conta.getTipo(), contaEncontrada.getTipo());
    }

    @Test
    void deveDepositarNormalmente() {
        service.criarConta("João", 1000, "poupanca");

        ContaBancaria conta = service.listarContas().get(0);
        service.depositar(conta.getId(), 500);
        ContaBancaria contaAtualizada = service.buscarPorId(conta.getId());

        assertEquals(1500, contaAtualizada.getSaldo());
        System.out.println(contaAtualizada);
    }

    @Test
    void deveSacarNormalmente() {
        service.criarConta("João", 1000, "poupanca");

        ContaBancaria conta = service.listarContas().get(0);
        service.sacar(conta.getId(), 300);
        ContaBancaria contaAtualizada = service.buscarPorId(conta.getId());

        assertEquals(700, contaAtualizada.getSaldo());
        System.out.println(contaAtualizada);
    }
    
    @Test
    void deveTransferirNormalmente() {
        service.criarConta("João", 1000, "poupanca");
        service.criarConta("Maria", 500, "corrente");

        ContaBancaria contaOrigem = service.listarContas().get(0);
        ContaBancaria contaDestino = service.listarContas().get(1);

        service.tranferencia(contaOrigem.getId(), contaDestino.getId(), 200);

        ContaBancaria contaOrigemAtualizada = service.buscarPorId(contaOrigem.getId());
        ContaBancaria contaDestinoAtualizada = service.buscarPorId(contaDestino.getId());

        assertEquals(800, contaOrigemAtualizada.getSaldo());
        assertEquals(700, contaDestinoAtualizada.getSaldo());

        System.out.println(contaOrigemAtualizada);
        System.out.println(contaDestinoAtualizada);
    }

    @Test
    void deveApagarConta() {
        service.criarConta("João", 1000, "poupanca");

        ContaBancaria conta = service.listarContas().get(0);
        service.apagarConta(conta.getId());

        ContaBancaria contaEncontrada = service.buscarPorId(conta.getId());
        assertNull(contaEncontrada);
        assertEquals(null, contaEncontrada);
    }
    
}
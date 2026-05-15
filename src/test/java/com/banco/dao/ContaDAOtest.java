package com.banco.dao;

import com.banco.config.SQLiteConnectionTest;
import com.banco.model.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ContaDAOtest {

    private ContaDAO dao;

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

        dao = new ContaDAO(conn);
    }

    @Test
    void deveSalvarConta() {

        ContaBancaria conta = new ContaCorrente("Maria",500);
            dao.salvar(conta);

        ContaBancaria contaSalva = dao.buscarPorId(conta.getId());
        assertNotNull(contaSalva);
        assertEquals("maria", contaSalva.getNome());

        assertEquals(500,contaSalva.getSaldo());

        assertEquals("corrente", contaSalva.getTipo());
    }

    @Test
    void deveListarContas() {

        ContaBancaria conta1 = new ContaCorrente("Maria",500);
        ContaBancaria conta2 = new ContaPoupanca("João",1000);

        dao.salvar(conta1);
        dao.salvar(conta2);
        
        List<ContaBancaria> contas = dao.listar();
        System.out.println(contas);

        assertEquals(2, contas.size());
    }

    @Test
    void deveAtualizarSaldo() {

        ContaBancaria conta = new ContaCorrente("Maria",500);
        dao.salvar(conta);

        dao.atualizarSaldo(conta.getId(), 800);

        ContaBancaria contaAtualizada = dao.buscarPorId(conta.getId());
        assertEquals(800, contaAtualizada.getSaldo());
    }

    @Test
    void deveBuscarPorId() {
        ContaBancaria conta = new ContaCorrente("Maria",500);
        dao.salvar(conta);

        ContaBancaria contaEncontrada = dao.buscarPorId(conta.getId());
        assertNotNull(contaEncontrada);
        assertEquals("maria", contaEncontrada.getNome());
    }

    @Test
    void deveExcluirConta() {
        ContaBancaria conta = new ContaCorrente("Maria",500);
        dao.salvar(conta);

        dao.excluirConta(conta.getId()); 

        ContaBancaria contaExcluida = dao.buscarPorId(conta.getId());
        assertNull(contaExcluida);
    }

}
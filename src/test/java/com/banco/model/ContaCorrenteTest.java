package com.banco.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContaCorrenteTest {

    @Test
    void deveImpedirDadosInvalidos() {
        assertAll(

            () -> assertThrows(IllegalArgumentException.class,
                () -> new ContaCorrente("", 100)),

            () -> assertThrows(IllegalArgumentException.class,
                () -> new ContaCorrente("Tom", -100)),

            () -> assertThrows(IllegalArgumentException.class,
                () -> new ContaCorrente(null, 100))
        );
    }

    @Test
    void deveSacarNormalmente() {
        ContaCorrente conta = new ContaCorrente("Tom", 100);

        conta.sacar(50);

        assertEquals(50, conta.getSaldo());
    }

    @Test
    void naoDeveSacarValorMaiorQueOSaldo() {
        ContaCorrente conta = new ContaCorrente("Tom", 100);

        assertThrows(IllegalArgumentException.class,
            () -> conta.sacar(150));
    }

    @Test
    void naoDeveSacarValorNegativo() {
        ContaCorrente conta = new ContaCorrente("Tom", 100);

        assertThrows(IllegalArgumentException.class,
            () -> conta.sacar(-50));
    }

    @Test
    void deveDepositarNormalmente() {
        ContaCorrente conta = new ContaCorrente("Tom", 100);

        conta.depositar(50);

        assertEquals(150, conta.getSaldo());
    }

    @Test
    void deveFormatarONomeAutomaticamente() {
        ContaCorrente conta = new ContaCorrente("   TOM   ", 100);

        assertEquals("tom", conta.getNome());
    }

    @Test
    void deveAlterarONomeCorretamente() {
        ContaCorrente conta = new ContaCorrente("Tom", 100);

        conta.setNome("   João   ");

        assertEquals("joão", conta.getNome());
    }

    @Test
    void naoDevePermitirNomeVazioAoAlterar() {
        ContaCorrente conta = new ContaCorrente("Tom", 100);

        assertThrows(IllegalArgumentException.class,
            () -> conta.setNome(""));
    }

    @Test
    void naoDevePermitirNomeNullAoAlterar() {
        ContaCorrente conta = new ContaCorrente("Tom", 100);

        assertThrows(IllegalArgumentException.class,
            () -> conta.setNome(null));
    }

    @Test
    void deveAlterarSaldoCorretamente() {
        ContaCorrente conta = new ContaCorrente("Tom", 100);

        conta.setSaldo(500);

        assertEquals(500, conta.getSaldo());
    }

    @Test
    void naoDevePermitirSaldoNegativoAoAlterar() {
        ContaCorrente conta = new ContaCorrente("Tom", 100);

        assertThrows(IllegalArgumentException.class,
            () -> conta.setSaldo(-10));
    }

    @Test
    void deveDefinirIdCorretamente() {
        ContaCorrente conta = new ContaCorrente("Tom", 100);

        conta.setId(1);

        assertEquals(1, conta.getId());
    }

    @Test
    void deveRetornarTipoCorretamente() {
        ContaCorrente conta = new ContaCorrente("Tom", 100);

        assertEquals("corrente", conta.getTipo());
    }

    @Test
    void deveRetornarToStringCorretamente() {
        ContaCorrente conta = new ContaCorrente("Tom", 100);

        String esperado = "corrente | Nome: tom | Saldo: 100.0";

        assertEquals(esperado, conta.toString());
    }
}
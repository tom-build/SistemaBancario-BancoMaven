package com.banco.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

    class ContaPoupancaTest {
    @Test
    void deveImpedirDadosInválidos(){
        assertAll(

            () -> assertThrows(IllegalArgumentException.class, 
                () -> new ContaPoupanca("", 100)),
            
            () -> assertThrows(IllegalArgumentException.class, 
                () -> new ContaPoupanca("Tom", -100)),

            () -> assertThrows(IllegalArgumentException.class, 
                () -> new ContaPoupanca(null, 100))
            );
    }

    @Test
    void deveSacarNormalmente(){
        ContaPoupanca conta = new ContaPoupanca("Tom", 100);
        conta.sacar(50);
        
        assertEquals(50, conta.getSaldo());
    }

    @Test
    void naoDeveSacarValorMaiorQueOSaldo(){
        ContaPoupanca conta = new ContaPoupanca("Tom", 100);
        assertThrows(IllegalArgumentException.class, () -> conta.sacar(150));
    }

    @Test
    void naoDeveSacarValorNegativo(){
        ContaPoupanca conta = new ContaPoupanca("Tom", 100);
        assertThrows(IllegalArgumentException.class, () -> conta.sacar(-50));
    }

    
    @Test
    void deveDepositarNormalmente() {
        ContaPoupanca conta = new ContaPoupanca("Tom", 100);

        conta.depositar(50);

        assertEquals(150, conta.getSaldo());
    }

    @Test
    void naoDeveDepositarValorNegativo() {
        ContaPoupanca conta = new ContaPoupanca("Tom", 100);

        assertThrows(IllegalArgumentException.class,
            () -> conta.depositar(-50));
    }

    @Test
    void deveFormatarONomeAutomaticamente() {
        ContaPoupanca conta = new ContaPoupanca("   TOM   ", 100);

        assertEquals("tom", conta.getNome());
    }

    @Test
    void deveAlterarONomeCorretamente() {
        ContaPoupanca conta = new ContaPoupanca("Tom", 100);

        conta.setNome("   João   ");

        assertEquals("joão", conta.getNome());
    }

    @Test
    void naoDevePermitirNomeVazioAoAlterar() {
        ContaPoupanca conta = new ContaPoupanca("Tom", 100);

        assertThrows(IllegalArgumentException.class,
            () -> conta.setNome(""));
    }

    @Test
    void naoDevePermitirNomeNullAoAlterar() {
        ContaPoupanca conta = new ContaPoupanca("Tom", 100);

        assertThrows(IllegalArgumentException.class,
            () -> conta.setNome(null));
    }

    @Test
    void deveAlterarSaldoCorretamente() {
        ContaPoupanca conta = new ContaPoupanca("Tom", 100);

        conta.setSaldo(500);

        assertEquals(500, conta.getSaldo());
    }

    @Test
    void naoDevePermitirSaldoNegativoAoAlterar() {
        ContaPoupanca conta = new ContaPoupanca("Tom", 100);

        assertThrows(IllegalArgumentException.class,
            () -> conta.setSaldo(-10));
    }

    @Test
    void deveDefinirIdCorretamente() {
        ContaPoupanca conta = new ContaPoupanca("Tom", 100);

        conta.setId(1);

        assertEquals(1, conta.getId());
    }

    @Test
    void deveRetornarTipoCorretamente() {
        ContaPoupanca conta = new ContaPoupanca("Tom", 100);

        assertEquals("poupanca", conta.getTipo());
    }

    @Test
    void deveRetornarToStringCorretamente() {
        ContaPoupanca conta = new ContaPoupanca("Tom", 100);

        String esperado = "poupanca | Nome: tom | Saldo: 100.0";

        assertEquals(esperado, conta.toString());
    }

}

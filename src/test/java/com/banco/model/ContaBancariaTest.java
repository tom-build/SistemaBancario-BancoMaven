package com.banco.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ContaBancariaTest {
    @Test
    void deveFormatarString() {
        assertEquals("tom", ContaBancaria.formatar("TOM"));
    }

    @Test
    void deveValidarInt() {
        assertThrows(IllegalArgumentException.class, () -> ContaBancaria.validarInt(-1));
    }

    @Test
    void deveRetornarToStringCorretamente() {
        ContaCorrente conta = new ContaCorrente("Tom", 100);

        String esperado = "corrente | Nome: tom | Saldo: 100.0";

        assertEquals(esperado, conta.toString());
    }
}

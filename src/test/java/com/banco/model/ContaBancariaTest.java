package com.banco.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ContaBancariaTest {
    @Test
    void deveFormatarString() {
        assertEquals("tom", ContaBancaria.formatar("TOM"));
    }
}

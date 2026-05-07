package com.banco.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ContaCorrenteTest {
    
    @Test
    void deveImpedirDadosInválidos(){
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
    void deveSacarNormalmente(){
        ContaCorrente conta = new ContaCorrente("Tom", 100);
        conta.sacar(50);
        
        assertEquals(50, conta.getSaldo());
    }

    @Test
    void naoDeveSacarValorMaiorQueOSaldo(){
        ContaCorrente conta = new ContaCorrente("Tom", 100);
        assertThrows(IllegalArgumentException.class, () -> conta.sacar(150));
    }

    @Test
    void naoDeveSacarValorNegativo(){
        ContaCorrente conta = new ContaCorrente("Tom", 100);
        assertThrows(IllegalArgumentException.class, () -> conta.sacar(-50));
    }
}
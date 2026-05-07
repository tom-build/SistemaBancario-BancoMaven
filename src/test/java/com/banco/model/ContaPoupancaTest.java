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

}

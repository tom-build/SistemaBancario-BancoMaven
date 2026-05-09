package com.banco.util;

import java.util.Scanner;

public class InputUtil {

    private static Scanner sc = new Scanner(System.in);

    public static String lerString(String mensagem) {
        while (true) {

            System.out.print(mensagem + ": ");

            String entrada = sc.nextLine();

            if (entrada.trim().isEmpty()) {
                System.out.println("O campo não pode ficar vazio.");
                continue;
            }

            return entrada;
        }
    }

    public static double lerDouble(String mensagem) {
        while (true) {

            System.out.print(mensagem + ": ");

            String entrada = sc.nextLine();

            if (entrada.trim().isEmpty()) {
                System.out.println("O valor não pode ficar vazio.");
                continue;
            }

            try {
                return Double.parseDouble(entrada);

            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido.");
            }
        }
    }

    public static int lerInt(String mensagem) {
        while (true) {

            System.out.print(mensagem + ": ");

            String entrada = sc.nextLine();

            if (entrada.trim().isEmpty()) {
                System.out.println("O valor não pode ficar vazio.");
                continue;
            }

            try {
                return Integer.parseInt(entrada);

            } catch (NumberFormatException e) {
                System.out.println("Digite um número inteiro válido.");
            }
        }
    }
}
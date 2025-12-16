package org.example;

import org.example.view.UseCase;

public class Main {

    public static void main(String[] args) {
        System.out.println("======================================");
        System.out.println("     SISTEMA DE CLIENTES - LOJA");
        System.out.println("======================================");
        System.out.println("Conectando ao sistema...");

        try {
           // UseCase.main(args);
        } catch (Exception e) {
            System.out.println("Erro fatal: " + e.getMessage());
        }

        System.out.println("Sistema encerrado.");
    }

}


// testando commit

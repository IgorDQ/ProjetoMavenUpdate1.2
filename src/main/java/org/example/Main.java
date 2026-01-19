/**
package org.example;
import org.example.model.ClienteDAO;
import org.example.dto.PessoaEnderecoDTO;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("======================================");
        System.out.println("     SISTEMA DE CLIENTES - LOJA");
        System.out.println("======================================");
        System.out.println("Conectando ao sistema...");

        try {
           // UseCase.main(args);
            List<PessoaEnderecoDTO> pessoas = new ArrayList<>();
            ClienteDAO C = new ClienteDAO();
            pessoas = C.listarPorLogradouro("rua sao tome");


        } catch (Exception e) {
            System.out.println("Erro fatal: " + e.getMessage());
        }

        System.out.println("Sistema encerrado.");
    }

}
 */




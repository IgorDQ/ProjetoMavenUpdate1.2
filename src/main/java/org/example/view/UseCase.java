package org.example.view;

import org.example.controller.ClienteController;
import org.example.model.Cliente;
import org.example.model.ClienteDAO;
import org.example.model.Endereco;
import org.example.model.EnderecoDAO;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UseCase {

    private static final ClienteController controller =
            new ClienteController(new ClienteDAO(), new EnderecoDAO());

    private static final Scanner sc = new Scanner(System.in);

    public static void cadastrar() throws SQLException {
        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Idade: ");
        int idade = sc.nextInt();
        sc.nextLine();

        System.out.print("Cidade: ");
        String cidade = sc.nextLine();

        System.out.print("Logradouro: ");
        String logradouro = sc.nextLine();

        System.out.print("Número: ");
        int numero = sc.nextInt();
        sc.nextLine();

        System.out.print("Complemento: ");
        String complemento = sc.nextLine();

        System.out.print("Município: ");
        String municipio = sc.nextLine();

        System.out.print("UF: ");
        String uf = sc.nextLine();

        System.out.print("País: ");
        String pais = sc.nextLine();

        Endereco endereco = new Endereco(
                logradouro,
                numero,
                complemento,
                municipio,
                uf,
                pais,
                true
        );

        Cliente cliente = new Cliente(nome, idade, cidade);
        cliente.setEndereco(endereco);

        controller.cadastrar(cliente);

        System.out.println("Cliente cadastrado com sucesso!");
    }

    public static void listar() throws SQLException {
        List<Cliente> clientes = controller.listar();
        clientes.forEach(System.out::println);
    }

    public static void atualizar() throws SQLException {
        System.out.print("ID do cliente: ");
        int id = sc.nextInt();
        sc.nextLine();

        Cliente existente = controller.buscarPorId(id);
        if (existente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        System.out.print("Novo nome: ");
        String nome = sc.nextLine();

        System.out.print("Nova idade: ");
        int idade = sc.nextInt();
        sc.nextLine();

        System.out.print("Nova cidade: ");
        String cidade = sc.nextLine();

        Cliente atualizado = new Cliente(
                id,
                nome,
                idade,
                cidade,
                existente.getEndereco()
        );

        controller.atualizar(atualizado);
        System.out.println("Cliente atualizado!");
    }
}

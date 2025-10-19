package org.example.view;

import org.example.controller.ClienteController;
import org.example.model.Cliente;
import org.example.model.Endereco;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ClienteView {

    private static final ClienteController controller = new ClienteController();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("--- Sistema de Cadastro de Clientes ---");

        try {
            while (true) {
                exibirMenu();
                int opcao = lerOpcao(sc);

                try {
                    switch (opcao) {
                        case 1:
                            cadastrar(sc, controller);
                            break;
                        case 2:
                            listar(controller);
                            break;
                        case 3:
                            buscar(sc, controller);
                            break;
                        case 4:
                            atualizar(sc, controller);
                            break;
                        case 5:
                            deletar(sc, controller);
                            break;
                        case 6:
                            System.out.println("Encerrando o sistema. Tchau!");
                            return;
                        default:
                            System.out.println("[ERRO] Opção inválida. Tente novamente.");
                    }
                } catch (SQLException e) {
                    System.out.println("\n[ERRO DE BANCO DE DADOS] Ocorreu um erro: " + e.getMessage());
                } catch (IllegalArgumentException e) {
                    System.out.println("\n[ERRO DE VALIDAÇÃO] " + e.getMessage());
                }
            }
        } finally {
            sc.close();
        }
    }

    private static void exibirMenu() {
        System.out.println("\n--- MENU ---");
        System.out.println("1. Cadastrar Cliente");
        System.out.println("2. Listar Clientes");
        System.out.println("3. Buscar Cliente por ID");
        System.out.println("4. Atualizar Cliente");
        System.out.println("5. Deletar Cliente");
        System.out.println("6. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static int lerOpcao(Scanner sc) {
        try {
            return sc.nextInt();
        } catch (InputMismatchException e) {
            sc.nextLine();
            return -1;
        } finally {
            sc.nextLine();
        }
    }

    private static int lerIdade(Scanner sc) {
        System.out.print("Idade: ");
        while (!sc.hasNextInt()) {
            System.out.println("[ERRO] Por favor, digite um número inteiro para a idade.");
            sc.next();
            System.out.print("Idade: ");
        }
        int idade = sc.nextInt();
        sc.nextLine();
        return idade;
    }

    private static void cadastrar(Scanner sc, ClienteController controller) throws SQLException {
        System.out.println("\n--- Dados do Cliente ---");
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        int idade = lerIdade(sc);
        System.out.print("Cidade: ");
        String cidade = sc.nextLine();

        System.out.println("\n--- Dados do Endereço ---");
        System.out.print("Logradouro: ");
        String logradouro = sc.nextLine();
        System.out.print("Número: ");
        String numero = sc.nextLine();
        System.out.print("Complemento (Opcional): ");
        String complemento = sc.nextLine();
        System.out.print("Município: ");
        String municipio = sc.nextLine();
        System.out.print("Unidade Federal (Ex: SP): ");
        String uf = sc.nextLine();
        System.out.print("País: ");
        String pais = sc.nextLine();

        Endereco novoEndereco = new Endereco(logradouro, numero, complemento, municipio, uf, pais);

        controller.cadastrar(nome, idade, cidade, novoEndereco);
        System.out.println("\n[SUCESSO] Cliente e Endereço cadastrados!");
    }

    private static void listar(ClienteController controller) throws SQLException {
        List<Cliente> clientes = controller.listar();
        if (clientes.isEmpty()) {
            System.out.println("\nNenhum cliente cadastrado.");
            return;
        }

        System.out.println("\n--- Lista de Clientes ---");
        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }
    }

    private static void buscar(Scanner sc, ClienteController controller) throws SQLException {
        System.out.println("\n--- Buscar Cliente ---");
        System.out.print("ID do Cliente: ");
        if (!sc.hasNextInt()) {
            System.out.println("[ERRO] ID deve ser um número.");
            sc.nextLine();
            return;
        }
        int id = sc.nextInt();
        sc.nextLine();

        Cliente cliente = controller.buscarPorId(id);
        if (cliente != null) {
            System.out.println("\n--- Resultado da Busca ---");
            System.out.println(cliente);
        } else {
            System.out.println("\nCliente com ID " + id + " não encontrado.");
        }
    }

    private static void atualizar(Scanner sc, ClienteController controller) throws SQLException {
        System.out.println("\n--- Atualizar Cliente ---");
        System.out.print("ID do Cliente a ser atualizado: ");
        if (!sc.hasNextInt()) {
            System.out.println("[ERRO] ID deve ser um número.");
            sc.nextLine();
            return;
        }
        int id = sc.nextInt();
        sc.nextLine();

        Cliente clienteExistente = controller.buscarPorId(id);
        if (clienteExistente == null) {
            System.out.println("\nCliente com ID " + id + " não encontrado.");
            return;
        }

        System.out.println("Cliente atual: " + clienteExistente);

        System.out.println("\nNovos Dados:");
        System.out.print("Novo Nome (" + clienteExistente.getNome() + "): ");
        String nome = sc.nextLine();
        if (nome.trim().isEmpty()) nome = clienteExistente.getNome();

        System.out.print("Nova Idade (" + clienteExistente.getIdade() + "): ");
        String idadeStr = sc.nextLine();
        int idade;
        if (idadeStr.trim().isEmpty()) {
            idade = clienteExistente.getIdade();
        } else {
            try {
                idade = Integer.parseInt(idadeStr);
            } catch (NumberFormatException e) {
                System.out.println("[ERRO] Idade inválida. Operação cancelada.");
                return;
            }
        }

        System.out.print("Nova Cidade (" + clienteExistente.getCidade() + "): ");
        String cidade = sc.nextLine();
        if (cidade.trim().isEmpty()) cidade = clienteExistente.getCidade();


        Cliente clienteAtualizado = new Cliente(id, nome, idade, cidade, clienteExistente.getEndereco());

        if (controller.atualizar(clienteAtualizado)) {
            System.out.println("\n[SUCESSO] Cliente ID " + id + " atualizado.");
        } else {
            System.out.println("\n[FALHA] Não foi possível atualizar o cliente.");
        }
    }

    private static void deletar(Scanner sc, ClienteController controller) throws SQLException {
        System.out.println("\n--- Deletar Cliente ---");
        System.out.print("ID do Cliente a ser deletado: ");
        if (!sc.hasNextInt()) {
            System.out.println("[ERRO] ID deve ser um número.");
            sc.nextLine();
            return;
        }
        int id = sc.nextInt();
        sc.nextLine();

        if (controller.deletar(id)) {
            System.out.println("\n[SUCESSO] Cliente ID " + id + " deletado (e seu endereço, via ON DELETE CASCADE).");
        } else {
            System.out.println("\n[FALHA] Cliente ID " + id + " não encontrado ou erro ao deletar.");
        }
    }
}

package org.example.controller;

import org.example.model.Cliente;
import org.example.model.ClienteDAO;
import org.example.model.Endereco;
import org.example.model.EnderecoDAO;

import java.sql.SQLException;
import java.util.List;

public class ClienteController {

    private final ClienteDAO clienteDao;
    private final EnderecoDAO enderecoDao;

    public ClienteController() {
        this(new ClienteDAO(), new EnderecoDAO());
    }

    // permite injeção de mocks
    public ClienteController(ClienteDAO clienteDao, EnderecoDAO enderecoDao) {
        this.clienteDao = clienteDao;
        this.enderecoDao = enderecoDao;
    }

    public void cadastrar(String nome, int idade, String cidade, Endereco endereco) throws SQLException {
        Cliente cliente = new Cliente(nome, idade, cidade);

        int clienteId = clienteDao.salvar(cliente);

        if (clienteId == -1) {
            throw new SQLException("Falha ao obter ID do cliente após o cadastro.");
        }

        enderecoDao.salvarComProcedure(clienteId, endereco);
    }

    public List<Cliente> listar() throws SQLException {
        return clienteDao.listar();
    }

    public Cliente buscarPorId(int id) throws SQLException {
        return clienteDao.buscarPorId(id);
    }

    public boolean atualizar(Cliente cliente) throws SQLException {
        return clienteDao.atualizar(cliente);
    }

    public boolean deletar(int id) throws SQLException {
        return clienteDao.deletar(id);
    }
}

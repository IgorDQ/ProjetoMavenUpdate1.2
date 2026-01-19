package org.example.controller;

import org.example.model.Cliente;
import org.example.model.ClienteDAO;
import org.example.model.EnderecoDAO;

import java.sql.SQLException;
import java.util.List;

public class ClienteController {

    private final ClienteDAO clienteDAO;
    private final EnderecoDAO enderecoDAO;

    public ClienteController(ClienteDAO clienteDAO, EnderecoDAO enderecoDAO) {
        this.clienteDAO = clienteDAO;
        this.enderecoDAO = enderecoDAO;
    }


    // CADASTRAR
    public void cadastrar(Cliente cliente) throws SQLException {
        clienteDAO.salvar(cliente);
        enderecoDAO.salvar(cliente.getId(), cliente.getEndereco());
    }


    // LISTAR
    public List<Cliente> listar() throws SQLException {
        return clienteDAO.listar();
    }


    // BUSCAR POR ID
    public Cliente buscarPorId(int id) throws SQLException {
        return clienteDAO.buscarPorId(id);
    }


    // ATUALIZAR
    public boolean atualizar(Cliente cliente) throws SQLException {
        return clienteDAO.atualizar(cliente);
    }


    // DELETAR
    public boolean deletar(int id) throws SQLException {
        return clienteDAO.deletar(id);
    }
}

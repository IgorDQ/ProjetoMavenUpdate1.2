package org.example.dto;

public class PessoaEnderecoDTO {

    private String nome;
    private int idade;
    private String cidade;
    private String estado;

    public PessoaEnderecoDTO(String nome, int idade, String cidade, String estado) {
        this.nome = nome;
        this.idade = idade;
        this.cidade = cidade;
        this.estado = estado;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }
}

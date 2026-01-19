package org.example.model;

public class Cliente {

    private int id;
    private String nome;
    private int idade;
    private String cidade;
    private Endereco endereco;


    public Cliente(String nome, int idade, String cidade) {
        this.nome = nome;
        this.idade = idade;
        this.cidade = cidade;
    }


    public Cliente(int id, String nome, int idade, String cidade, Endereco endereco) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.cidade = cidade;
        this.endereco = endereco;
    }

    public int getId() {
        return id;
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

    public Endereco getEndereco() {
        return endereco;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }


    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", idade=" + idade +
                ", cidade='" + cidade + '\'' +
                ", endereco=" + endereco +
                '}';
    }
}

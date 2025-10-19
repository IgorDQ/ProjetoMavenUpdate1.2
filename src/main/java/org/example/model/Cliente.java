package org.example.model;

public class Cliente {
    private int id;
    private String nome;
    private int idade;
    private String cidade;
    private Endereco endereco;


    public Cliente(int id, String nome, int idade, String cidade, Endereco endereco) {

        this.id = id;
        this.nome = nome.trim();
        this.idade = idade;
        this.cidade = cidade.trim();
        this.endereco = endereco;
    }


    public Cliente(String nome, int idade, String cidade) {
        this.nome = nome.trim();
        this.idade = idade;
        this.cidade = cidade.trim();
        this.endereco = null;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public int getIdade() { return idade; }
    public String getCidade() { return cidade; }
    public Endereco getEndereco() { return endereco; }

    @Override
    public String toString() {
        String dadosEndereco = (endereco != null)
                ? " | Endereço: " + endereco.toString()
                : " | Endereço: N/A";
        return "ID: " + id + " | Nome: " + nome + " | Idade: " + idade + " | Cidade: " + cidade + dadosEndereco;
    }
}
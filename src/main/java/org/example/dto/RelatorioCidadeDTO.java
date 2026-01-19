package org.example.dto;

public class RelatorioCidadeDTO {

    private String cidade;
    private String estado;
    private int quantidadePessoas;
    private double idadeMedia;

    public RelatorioCidadeDTO(String cidade,
                              String estado,
                              int quantidadePessoas,
                              double idadeMedia) {
        this.cidade = cidade;
        this.estado = estado;
        this.quantidadePessoas = quantidadePessoas;
        this.idadeMedia = idadeMedia;
    }

    public RelatorioCidadeDTO() {
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getQuantidadePessoas() {
        return quantidadePessoas;
    }

    public void setQuantidadePessoas(int quantidadePessoas) {
        this.quantidadePessoas = quantidadePessoas;
    }

    public double getIdadeMedia() {
        return idadeMedia;
    }

    public void setIdadeMedia(double idadeMedia) {
        this.idadeMedia = idadeMedia;
    }
}

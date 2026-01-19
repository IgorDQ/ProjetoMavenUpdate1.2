package org.example.dto;

public class RelatorioEstadoDTO {

    private String estado;
    private int quantidadePessoas;
    private double idadeMedia;

    public RelatorioEstadoDTO(String estado,
                              int quantidadePessoas,
                              double idadeMedia) {
        this.estado = estado;
        this.quantidadePessoas = quantidadePessoas;
        this.idadeMedia = idadeMedia;
    }

    public RelatorioEstadoDTO() {
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

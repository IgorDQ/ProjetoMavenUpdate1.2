package org.example.model;

public class Endereco {

    private String logradouro;
    private int numero;
    private String complemento;
    private String municipio;
    private String uf;
    private String pais;
    private boolean ativo;

    public Endereco(
            String logradouro,
            int numero,
            String complemento,
            String municipio,
            String uf,
            String pais,
            boolean ativo
    ) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.municipio = municipio;
        this.uf = uf;
        this.pais = pais;
        this.ativo = ativo;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public int getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getMunicipio() {
        return municipio;
    }

    public String getUf() {
        return uf;
    }

    public String getPais() {
        return pais;
    }

    public boolean isAtivo() {
        return ativo;
    }
}

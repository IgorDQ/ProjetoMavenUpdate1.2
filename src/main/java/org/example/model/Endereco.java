package org.example.model;

public class Endereco {
    private String logradouro;
    private String numero;
    private String complemento;
    private String municipio;
    private String unidadeFederal;
    private String pais;

    public Endereco(String logradouro, String numero, String complemento, String municipio, String unidadeFederal, String pais) {
        validarCampos(logradouro, municipio, unidadeFederal, pais);
        this.logradouro = logradouro.trim();
        this.numero = numero != null ? numero.trim() : "";
        this.complemento = complemento != null ? complemento.trim() : "";
        this.municipio = municipio.trim();
        this.unidadeFederal = unidadeFederal.trim().toUpperCase();
        this.pais = pais.trim();
    }

    public Endereco(String logradouro, String numero, String complemento, String municipio, String unidadeFederal, String pais, boolean isNull) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.municipio = municipio;
        this.unidadeFederal = unidadeFederal;
        this.pais = pais;
    }

    private void validarCampos(String logradouro, String municipio, String uf, String pais) {
        if (logradouro == null || logradouro.trim().length() < 3) throw new IllegalArgumentException("Logradouro inválido.");
        if (municipio == null || municipio.trim().length() < 3) throw new IllegalArgumentException("Município inválido.");
        if (uf == null || uf.trim().length() != 2 || !uf.trim().matches("[A-Z]{2}")) throw new IllegalArgumentException("UF inválida. Use 2 caracteres.");
        if (pais == null || pais.trim().length() < 3) throw new IllegalArgumentException("País inválido.");
    }

    public String getLogradouro() { return logradouro; }
    public String getNumero() { return numero; }
    public String getComplemento() { return complemento; }
    public String getMunicipio() { return municipio; }
    public String getUnidadeFederal() { return unidadeFederal; }
    public String getPais() { return pais; }

    @Override
    public String toString() {
        String comp = complemento.isEmpty() ? "" : " | Comp: " + complemento;
        return String.format("%s, %s%s | %s - %s (%s)", logradouro, numero, comp, municipio, unidadeFederal, pais);
    }
}
package org.example.view;

public class ValidadorCampos {

    public static void validarNomeOuCidade(String valor, String nomeCampo) {
        if (valor == null || valor.trim().length() < 3) {
            throw new IllegalArgumentException(nomeCampo + " inválido. Deve ter pelo menos 3 caracteres.");
        }
    }

    public static void validarIdade(int idade) {
        if (idade <= 0 || idade > 200) {
            throw new IllegalArgumentException("Idade inválida. Deve estar entre 1 e 200 anos.");
        }
    }

    public static void validarLogradouro(String logradouro) {
        if (logradouro == null || logradouro.trim().length() < 3) {
            throw new IllegalArgumentException("Logradouro inválido. Deve ter pelo menos 3 caracteres.");
        }
    }

    public static String normalizarNumeroOuComplemento(String valor) {
        return (valor == null) ? "" : valor.trim();
    }

    public static void validarMunicipio(String municipio) {
        if (municipio == null || municipio.trim().length() < 3) {
            throw new IllegalArgumentException("Município inválido. Deve ter pelo menos 3 caracteres.");
        }
    }

    public static void validarUF(String uf) {
        if (uf == null || !uf.trim().matches("[A-Z]{2}")) {
            throw new IllegalArgumentException("UF inválida. Use 2 letras maiúsculas.");
        }
    }

    public static void validarPais(String pais) {
        if (pais == null || pais.trim().length() < 3) {
            throw new IllegalArgumentException("País inválido. Deve ter pelo menos 3 caracteres.");
        }
    }

    public static void validarEndereco(String logradouro, String numero, String complemento,
                                       String municipio, String uf, String pais) {
        validarLogradouro(logradouro);
        normalizarNumeroOuComplemento(numero);
        normalizarNumeroOuComplemento(complemento);
        validarMunicipio(municipio);
        validarUF(uf);
        validarPais(pais);
    }
}

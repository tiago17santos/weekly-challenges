package com.sistema_cadastro.dominio;

public class Pergunta {
    private int id;
    private String descricao;

    public Pergunta() {}

    public Pergunta(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

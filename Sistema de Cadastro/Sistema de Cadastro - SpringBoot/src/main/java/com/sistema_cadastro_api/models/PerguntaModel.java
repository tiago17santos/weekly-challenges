package com.sistema_cadastro_api.models;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "perguntas")
public class PerguntaModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String descricao;

    public PerguntaModel() {
    }

    public PerguntaModel(Long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}

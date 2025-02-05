package com.techVerse.EventHub.entities;

import jakarta.persistence.*;
import jakarta.servlet.http.Part;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_atividade")
public class Atividade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    @Column(columnDefinition = "text")
    private String descricao;
    private double preco;

    @OneToMany(mappedBy = "atividades")
    private List<Participante> participantes = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "categoria")
    private Categoria categoria;

    @OneToOne
    @MapsId
    private Bloco bloco;


    public Atividade() {}

    public Atividade(int id, String nome, String descricao, double preco, Categoria categoria, Bloco bloco) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.categoria = categoria;
        this.bloco = bloco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Bloco getBloco() {
        return bloco;
    }

    public void setBloco(Bloco bloco) {
        this.bloco = bloco;
    }

    public List<Participante> getParticipantes() {
        return participantes;
    }
}

package com.techVerse.EventHub.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tb_participante")
public class Participante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private String email;

    @ManyToOne
    @JoinColumn(name = "atividades")
    private Atividade atividades;

    public Participante() {}

    public Participante(int id, String nome, String email, Atividade atividades) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.atividades = atividades;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Atividade getAtividades() {
        return atividades;
    }

    public void setAtividades(Atividade atividades) {
        this.atividades = atividades;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participante that = (Participante) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

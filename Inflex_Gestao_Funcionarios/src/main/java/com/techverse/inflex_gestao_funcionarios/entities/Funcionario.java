package com.techverse.inflex_gestao_funcionarios.entities;

import java.time.LocalDate;

public class Funcionario extends Pessoa {

    private Double salario;
    private String cargo;


    public Funcionario(String nome, LocalDate dataNascimento, Double salario, String cargo) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.cargo = cargo;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}

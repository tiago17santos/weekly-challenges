package com.sistema_cadastro.dominio;

import java.util.Objects;

public class Pessoa {
    private int id;
    private String nome;
    private String email;
    private int idade;
    private double altura;


    public static final class PessoaBuilder {
        private int id;
        private String nome;
        private String email;
        private int idade;
        private double altura;

        private PessoaBuilder(){
        }

        public static PessoaBuilder builder(){
            return new PessoaBuilder();
        }

        public PessoaBuilder id (int id){
            this.id = id;
            return this;
        }

        public PessoaBuilder nome (String nome){
            this.nome = nome;
            return this;
        }

        public PessoaBuilder email (String email){
            this.email = email;
            return this;
        }

        public PessoaBuilder idade (int idade){
            this.idade = idade;
            return this;
        }

        public PessoaBuilder altura (double altura){
            this.altura = altura;
            return this;
        }

        public Pessoa build(){
            Pessoa pessoa = new Pessoa();
            pessoa.id = id;
            pessoa.nome = nome;
            pessoa.email = email;
            pessoa.idade = idade;
            pessoa.altura = altura;
            return pessoa;
        }
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

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return idade == pessoa.idade && Double.compare(altura, pessoa.altura) == 0 && Objects.equals(nome, pessoa.nome) && Objects.equals(email, pessoa.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, email, idade, altura);
    }
}

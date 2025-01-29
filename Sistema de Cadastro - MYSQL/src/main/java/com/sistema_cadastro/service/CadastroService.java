package com.sistema_cadastro.service;

import com.sistema_cadastro.dominio.Pergunta;
import com.sistema_cadastro.dominio.Pessoa;
import com.sistema_cadastro.repository.CadastroRpository;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class CadastroService {

    private static Scanner sc = new Scanner(System.in);


    public static void buildMenu(int op) {
        switch(op){
            case 1: cadastrarPessoas();
            break;
            case 2: listarPessoas();
            break;
            case 3: cadastrarPergunta();
            break;
            case 4: deletarPergunta();
            break;
            case 5: pesquisarPessoaMenu();
            break;
            default: System.out.println("Opcao invalida");

        }
    }

    public static void listarPergunta() {
        List<Pergunta> perguntaList = CadastroRpository.findAllPerguntas();
        for (Pergunta pergunta : perguntaList) {
            System.out.println(pergunta.getId() + " - " +pergunta.getDescricao());
        }
    }

    public static void cadastrarPessoas() {
        sc.useLocale(Locale.US);
        listarPergunta();

        String nome = sc.nextLine();
        String email = sc.nextLine();

        int idade = sc.nextInt();
        sc.nextLine();
        double altura = sc.nextDouble();
        sc.nextLine();
        Pessoa pessoa = Pessoa.PessoaBuilder.builder()
                .nome(nome)
                .email(email)
                .idade(idade)
                .altura(altura)
                .build();

        CadastroRpository.insertPessoas(pessoa);
    }

    public static void listarPessoas() {
        List<Pessoa> pessoas = CadastroRpository.findAllPessoa();
        for (Pessoa pessoa : pessoas) {
            System.out.println(pessoa.getId() + " - " +pessoa.getNome());
        }
    }

    public static void cadastrarPergunta() {
        System.out.println("Qual pergunta você deseja salvar?");
        String perg = sc.next();
        CadastroRpository.insertPerguntas(perg);
    }

    public static void deletarPergunta() {
        listarPergunta();
        System.out.println("Digite o número da pergunta que deseja excluir: ");
        int op = sc.nextInt();
        if (op == 1 || op == 2 || op == 3 || op == 4){
            System.out.println("Essa pergunta não pode ser deletada! ");
        }else {
            CadastroRpository.deletarPergunta(op);
        }
    }

    public static void pesquisarPessoaMenu() {
        System.out.println("Você deseja encontar por 1-nome / 2-idade / 3-email?");
        int op = sc.nextInt();

        switch (op) {
            case 1: pesquisarPessoaPorNome(); break;
            case 2: pesquisarPessoaPorIdade(); break;
            case 3: pesquisarPessoaPorEmail(); break;
            default:System.out.println("Opcao invalida");
        }

    }
    public static void pesquisarPessoaPorNome(){
        System.out.println("Digite o nome da pessoa que deseja encontrar");
        String nome = sc.next();

        List<Pessoa> pessoas = CadastroRpository.findPessoaByName(nome);
        for (Pessoa pessoa : pessoas) {
            System.out.println(pessoa.getId() + " - " +pessoa.getNome());
        }
    }

    public static void pesquisarPessoaPorIdade(){
        System.out.println("Digite a idade da pessoa que deseja encontrar");
        int idade = sc.nextInt();

        List<Pessoa> pessoas = CadastroRpository.findPessoaByAge(idade);
        for (Pessoa pessoa : pessoas) {
            System.out.println(pessoa.getId() + " - " +pessoa.getNome());
        }
    }

    public static void pesquisarPessoaPorEmail(){
        System.out.println("Digite o email da pessoa que deseja encontrar");
        String email = sc.next();

        List<Pessoa> pessoas = CadastroRpository.findPessoaByEmail(email);
        for (Pessoa pessoa : pessoas) {
            System.out.println(pessoa.getId() + " - " +pessoa.getNome());
        }
    }





}

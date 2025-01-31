package com.sistema_cadastro.service;

import com.sistema_cadastro.dominio.Pergunta;
import com.sistema_cadastro.dominio.Pessoa;
import com.sistema_cadastro.repository.CadastroRepository;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class CadastroService {

    static Scanner sc = new Scanner(System.in);


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
        List<Pergunta> perguntaList = CadastroRepository.findAllPerguntas();
        for (Pergunta pergunta : perguntaList) {
            System.out.println(pergunta.getId() + " - " +pergunta.getDescricao());
        }
    }

    public static class CadastroException extends Exception {
        public CadastroException(String message) {
            super(message);
        }
    }

    public static boolean verificaEmail(String emailExistente) {
         return CadastroRepository.verificaEmailCadastrado(emailExistente);
    }

    public static void cadastrarPessoas() {
        sc.useLocale(Locale.US);
        listarPergunta();

        String nome = sc.nextLine();
        while (nome.length() < 10) {
            try {
                throw new CadastroException("Nome inválido, ele deve conter mais que 10 caracteres.");
            } catch (CadastroException e) {
                System.out.println(e.getMessage());
                nome = sc.nextLine();
            }
        }


        String email = sc.nextLine();
        while (!email.contains("@")) {
            try {
                throw new CadastroException("Digite o e-mail novamente, está faltando  '@..'");
            } catch (CadastroException e) {
                System.out.println(e.getMessage());
                email = sc.nextLine();
            }
        }

        while (verificaEmail(email)) {
            try {
                throw new CadastroException("Esse e-mail já existe, inclua outro.");
            } catch (CadastroException j) {
                System.out.println(j.getMessage());
                email = sc.nextLine();
            }
        }

        int idade = sc.nextInt();
        while (idade < 18) {
            try {
                throw new CadastroException("Você deve ser maior que 18 anos!");
            } catch (CadastroException e) {
                System.out.println(e.getMessage());
                idade = sc.nextInt();
            }
        }


        sc.nextLine();
        double altura = sc.nextDouble();
        sc.nextLine();
        Pessoa pessoa = Pessoa.PessoaBuilder.builder()
                .nome(nome)
                .email(email)
                .idade(idade)
                .altura(altura)
                .build();

        CadastroRepository.insertPessoas(pessoa);
    }

    public static void listarPessoas() {
        List<Pessoa> pessoas = CadastroRepository.findAllPessoa();
        for (Pessoa pessoa : pessoas) {
            System.out.println(pessoa.getId() + " - " +pessoa.getNome());
        }
    }

    public static void cadastrarPergunta() {
        System.out.println("Qual pergunta você deseja salvar?");
        String perg = sc.nextLine();
        CadastroRepository.insertPerguntas(perg);
    }

    public static void deletarPergunta() {
        listarPergunta();
        System.out.println("Digite o número da pergunta que deseja excluir: ");
        int op = sc.nextInt();
        if (op <= 4){
            throw new RuntimeException("Essa pergunta não pode ser deletada!");
        }else {
            CadastroRepository.deletePergunta(op);
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

        List<Pessoa> pessoas = CadastroRepository.findByNome(nome);

        if (pessoas.isEmpty()) {
            return; // Não imprime nada se a lista estiver vazia
        }

        for (Pessoa pessoa : pessoas) {
            System.out.println(pessoa.getId() + " - " +pessoa.getNome());
        }
    }

    public static void pesquisarPessoaPorIdade(){
        System.out.println("Digite a idade da pessoa que deseja encontrar");
        int idade = sc.nextInt();

        List<Pessoa> pessoas = CadastroRepository.findByAge(idade);
        for (Pessoa pessoa : pessoas) {
            System.out.println(pessoa.getId() + " - " +pessoa.getNome());
        }
    }

    public static void pesquisarPessoaPorEmail(){
        System.out.println("Digite o email da pessoa que deseja encontrar");
        String email = sc.next();

        List<Pessoa> pessoas = CadastroRepository.findByEmail(email);
        for (Pessoa pessoa : pessoas) {
            System.out.println(pessoa.getId() + " - " +pessoa.getNome());
        }
    }





}

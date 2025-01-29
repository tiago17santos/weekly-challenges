package com.sistema_cadastro.test;


import com.sistema_cadastro.service.CadastroService;

import java.util.Scanner;

public class CadastroTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while(true){
            menu();
            int op = sc.nextInt();
            if (op == 6) break;
            CadastroService.buildMenu(op);
        }
    }

    public static void menu(){
        System.out.println("\n-------------------------");
        System.out.println("1 - Cadastrar o usuário");
        System.out.println("2 - Listar todos usuários cadastrados");
        System.out.println("3 - Cadastrar nova pergunta no formulário");
        System.out.println("4 - Deletar pergunta do formulário");
        System.out.println("5 - Pesquisar usuário por nome ou idade ou email");
        System.out.println("6 - Sair");
    }
}



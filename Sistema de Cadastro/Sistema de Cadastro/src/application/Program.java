package application;

import entities.Functions;
import entities.Pessoa;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        Functions func = new Functions();
        boolean saiu = false;
        do{
            switch (func.menu()){
                case 1:
                    func.lePerguntas();
                    func.cadastrarUsuario();
                    func.salvaRespostaArquivo();
                    break;

                case 2:
                    func.listarUsuarios();
                    break;

                case 3:
                    func.cadastrarPerg();
                    break;

                case 4:
                    func.removerPerg();
                    break;

                case 5:
                    //Pesquisar usuário por nome ou idade ou email;
                    func.psqUsuario();
                    break;
                case 6:
                    saiu = true;
                    break;
            }
        }
        while (!saiu);
    }
}

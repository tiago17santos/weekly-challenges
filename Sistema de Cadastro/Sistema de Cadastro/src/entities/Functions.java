package entities;
import entities.Pessoa;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Functions {

    private String nome;
    private String email;
    private int idade;
    private String altura;
    static List<Pessoa> lista = new ArrayList<>();

    public Functions(){
        super();
    }

    public Functions(String nome, String email, int idade, String altura){
        this.nome = nome;
        this.email = email;
        this.idade = idade;
        this.altura = altura;
    }

    public int menu(){
        Scanner sc = new Scanner(System.in);
        System.out.println("1 - Cadastrar o usuário");
        System.out.println("2 - Listar todos usuários cadastrados");
        System.out.println("3 - Cadastrar nova pergunta no formulário");
        System.out.println("4 - Deletar pergunta do formulário");
        System.out.println("5 - Pesquisar usuário por nome ou idade ou email");
        int op = sc.nextInt();
        return op;
    }

    public void lePerguntas(){
        String pathIn = "C:/Users/tiago/OneDrive/Área de Trabalho/JAVA/exercicios/Sistemas/Sistema de Cadastro/formulario.txt";


        try(BufferedReader br = new BufferedReader(new FileReader(pathIn))){
            String line = br.readLine();
            while(line != null){
                System.out.println(line);
                line = br.readLine();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void cadastrarUsuario(){
        Scanner sc = new Scanner(System.in);

        nome = sc.next();
        email = sc.next();
        idade = sc.nextInt();
        altura = sc.next();

        lista.add(new Pessoa(nome, email,idade,altura));


        sc.close();
    }

    public void salvaRespostaArquivo(){

        String nomeArq = nome.replace(" ","").toUpperCase();
        String pathOut = "C:/Users/tiago/OneDrive/Área de Trabalho/JAVA/exercicios/Sistemas/Sistema de Cadastro/" + nomeArq + ".txt";

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(pathOut))){
            for(Pessoa line : lista){
                bw.write(line.getNome());
                bw.newLine();
                bw.write(line.getEmail());
                bw.newLine();
                bw.write(line.getIdade());
                bw.newLine();
                bw.write(line.getAltura());
                bw.newLine();
            }
            System.out.println("Arquivo salvo");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void listarUsuarios(){

        String pathOut = "C:/Users/tiago/OneDrive/Área de Trabalho/JAVA/exercicios/Sistemas/Sistema de Cadastro/arquivos";
        File file = new File(pathOut);
        File afile[] = file.listFiles();
        int i = 0;
        for (int j = afile.length; i < j; i++) {
            File arquivos = afile[i];
            String nomeArq = arquivos.getName().replace(".txt","").toUpperCase();
            System.out.println(nomeArq);
        }

    }
    public void cadastrarPerg(){
        String pathIn = "C:/Users/tiago/OneDrive/Área de Trabalho/JAVA/exercicios/Sistemas/Sistema de Cadastro/formulario.txt";

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(pathIn))){
            Scanner sc = new Scanner(System.in);

            System.out.println("Qual pergunta você deseja salavar?");
            String perg = sc.next();

            String pergAdd = "1 -" + perg;

            bw.newLine();
            bw.write(pergAdd);

            System.out.println("Arquivo salvo");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void removerPerg(){}

    public void psqUsuario(){}

}

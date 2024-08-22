package entities;
import entities.Pessoa;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Functions {

    private String nome;
    private String email;
    private String idade;
    private String altura;
    static List<Pessoa> lista = new ArrayList<>();

    public Functions(){
        super();
    }

    public Functions(String nome, String email, String idade, String altura){
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
        System.out.println("5 - Pesquisar usuário por nome");
        System.out.println("6 - Sair");
        int op = sc.nextInt();
        return op;
    }

    public List lePerguntas() {
        String pathIn = "C:/Users/tiago/OneDrive/Área de Trabalho/JAVA/exercicios/Sistemas/Sistema de Cadastro/formulario.txt";

        List lineList = new ArrayList();

        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(pathIn))) {
            line = br.readLine();
            while (line != null) {
                System.out.println(line);
                lineList.add(line);
                line = br.readLine();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return lineList;
    }

    public void cadastrarUsuario(){
        Scanner sc = new Scanner(System.in);

        System.out.print("Qual seu nome: ");
        nome = sc.nextLine();
        System.out.print("Qual seu email: ");
        email = sc.nextLine();
        System.out.print("Qual sua idade: ");
        idade = sc.nextLine();
        System.out.print("Qual seu altura: ");
        altura = sc.nextLine();

        lista.add(new Pessoa(nome, email,idade,altura));

    }

    public void salvaRespostaArquivo(){

        String nomeArq = nome.replace(" ","").toUpperCase();
        String pathOut = "C:/Users/tiago/OneDrive/Área de Trabalho/JAVA/exercicios/Sistemas/Sistema de Cadastro/arquivos/" + nomeArq + ".txt";

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
        String pathIn = "C:/Users/tiago/OneDrive/Área de Trabalho/JAVA/exercicios/Sistemas/Sistema de Cadastro/arquivos";

        String line;

        try{
            File pasta = new File(pathIn);
            File[] lists = pasta.listFiles();
            int cont = 1;
            for (File file : lists) {
                if (file.isFile() && file.getName().endsWith(".txt")) {

                    BufferedReader br = new BufferedReader(new FileReader(file));
                    line = br.readLine();
                    for (int i = 0; i==0;i++){
                        System.out.println(cont + "- " + line);
                        cont++;
                    }
                    br.close();
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void cadastrarPerg(){
        String pathIn = "C:/Users/tiago/OneDrive/Área de Trabalho/JAVA/exercicios/Sistemas/Sistema de Cadastro/formulario.txt";

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(pathIn, true))){
            Scanner sc = new Scanner(System.in);

            System.out.println("Qual pergunta você deseja salvar?");
            String perg = sc.nextLine();
            //Lê o arquivo e joga as perguntas em umas lista
            // depois verificar o tamanho dela para adicionar a proxima pergunta
            //verificar le perguntas - está lendo somente a ultima adicionada
            String pergAdd = "1- " + perg;

            bw.write("\n\r");
            bw.write(pergAdd);

            System.out.println("Arquivo salvo");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void removerPerg() throws IOException {
        String pathIn = "C:/Users/tiago/OneDrive/Área de Trabalho/JAVA/exercicios/Sistemas/Sistema de Cadastro/formulario.txt";

        List ArqPerg = lePerguntas();

        Scanner sc = new Scanner(System.in);
        System.out.println("Qual pergunta você deseja remover? (Digite o número dela)");
        String perg = sc.next();

        FileWriter fileWriter = new FileWriter(pathIn, false);

        try{
            for(Object line : ArqPerg) {
                if (!perg.contains("1") && !perg.contains("2") && !perg.contains("3") && !perg.contains("4") ){
                    if(!line.toString().contains(perg)) {
                        fileWriter.write(line + "\r\n");
                    }
                }
                else {
                   System.out.println("Essa pergunta não pode ser excluida");
                   return;
                }
            }

            System.out.println("Pergunta deletada");

        } catch (IOException e) {
            throw new RuntimeException(e);

        }finally   {
            try {
                fileWriter.close();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void psqUsuario() throws IOException {
        String pathIn = "C:/Users/tiago/OneDrive/Área de Trabalho/JAVA/exercicios/Sistemas/Sistema de Cadastro/arquivos";

        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o nome do usuario que você deseja pesquisar: ");
        String nomePsq = sc.next();
        String line;

        try{
            File pasta = new File(pathIn);
            File[] lists = pasta.listFiles();

            for (File file : lists) {
                if (file.isFile() && file.getName().endsWith(".txt")) {

                    if (file.getName().contains(nomePsq)){
                        BufferedReader br = new BufferedReader(new FileReader(file));
                        line = br.readLine();
                        System.out.println("=============");

                        while (line != null) {

                            System.out.println(line);
                            line = br.readLine();
                        }
                        br.close();
                    }
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

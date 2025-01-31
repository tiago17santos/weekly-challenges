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

        System.out.println("\n1 - Cadastrar o usuário");
        System.out.println("2 - Listar todos usuários cadastrados");
        System.out.println("3 - Cadastrar nova pergunta no formulário");
        System.out.println("4 - Deletar pergunta do formulário");
        System.out.println("5 - Pesquisar usuário por nome");
        System.out.println("6 - Sair");
        int op = sc.nextInt();
        return op;
    }

    public List lePerguntas() {
        String pathIn = "C:/Users/tiago/Desktop/JAVA/exercicios/Sistemas/Sistema de Cadastro/formulario.txt";

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

    public class CadastroException extends Exception {
        public CadastroException(String message) {
            super(message);
        }
    }

    public void cadastrarUsuario() throws CadastroException {
        Scanner sc = new Scanner(System.in);
        lePerguntas();

        nome = sc.nextLine();
        while (nome.length() < 10) {
            try {
                throw new CadastroException("Nome inválido, ele deve conter mais que 10 caracteres.");
            } catch (CadastroException e) {
                System.out.println(e.getMessage());
                nome = sc.nextLine();
            }
        }


        email = sc.nextLine();
        while (!email.contains("@")) {
            try {
                throw new CadastroException("Digite o e-mail novamente, está faltando  '@..'");
            } catch (CadastroException e) {
                System.out.println(e.getMessage());
                email = sc.nextLine();
                if (verificaEmail(email)) {
                    while (verificaEmail(email)) {
                        try {
                            throw new CadastroException("Esse e-mail ja existe, inclua outro.");
                        } catch (CadastroException j) {
                            System.out.println(j.getMessage());
                            email = sc.nextLine();
                        }
                    }
                }
            }
        }

        idade = sc.nextInt();
        while (idade < 18) {
            try {
                throw new CadastroException("Você deve ser maior que 18 anos!");
            } catch (CadastroException e) {
                System.out.println(e.getMessage());
                idade = sc.nextInt();
            }
        }


        sc.nextLine();
        altura = sc.nextLine();

        lista.add(new Pessoa(nome, email,idade,altura));

    }

    public boolean verificaEmail(String emailExistente) {
        String pathIn = "C:/Users/tiago/Desktop/JAVA/exercicios/Sistemas/Sistema de Cadastro/arquivos";
        String line;

        try{
            File pasta = new File(pathIn);
            File[] lists = pasta.listFiles();
            for (File file : lists) {
                if (file.isFile() && file.getName().endsWith(".txt")) {
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    line = br.readLine();
                    while (line != null) {
                        if (line.contains(emailExistente)) {
                            return true;
                        }
                        line = br.readLine();
                    }
                    br.close();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public void salvaRespostaArquivo(){

        String nomeArq = nome.replace(" ","").toUpperCase();
        String pathOut = "C:/Users/tiago/Desktop/JAVA/exercicios/Sistemas/Sistema de Cadastro/arquivos/" + nomeArq + ".txt";

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(pathOut))){
            for(Pessoa line : lista){
                bw.write(line.getNome());
                bw.newLine();
                bw.write(line.getEmail());
                bw.newLine();
                bw.write(String.valueOf(line.getIdade()));
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
        String pathIn = "C:/Users/tiago/Desktop/JAVA/exercicios/Sistemas/Sistema de Cadastro/arquivos";

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
                        System.out.println("\t" + cont + " - " + line);
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
        String pathIn = "C:/Users/tiago/Desktop/JAVA/exercicios/Sistemas/Sistema de Cadastro/formulario.txt";

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(pathIn, true))){
            Scanner sc = new Scanner(System.in);

            System.out.println("Qual pergunta você deseja salvar?");
            String perg = sc.nextLine();

            FileReader fr = new FileReader(pathIn);
            BufferedReader br = new BufferedReader(fr);

            List<String> listaPerg = new ArrayList<>();
            String line = br.readLine();

            while (line != null) {
                listaPerg.add(line);
                line = br.readLine();
            }

            int tamanhoLista = listaPerg.size() + 1;

            String pergAdd = "\n" + tamanhoLista + "- "  + perg ;

            bw.write(pergAdd);

            System.out.println("Arquivo salvo");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void removerPerg() throws IOException {
        String pathIn = "C:/Users/tiago/Desktop/JAVA/exercicios/Sistemas/Sistema de Cadastro/formulario.txt";

        List<String>  ArqPerg = lePerguntas();

        Scanner sc = new Scanner(System.in);
        System.out.println("Qual pergunta você deseja remover? (Digite o número dela)");
        String perg = sc.next();

        // Verifica se a pergunta a ser excluída é válida (1 a 4).
        if (perg.equals("1") || perg.equals("2") || perg.equals("3") || perg.equals("4")) {
            System.out.println("Essa pergunta não pode ser excluída");
            return;
        }

        // Filtra as perguntas que não devem ser excluídas
        List<String> novasPerguntas = new ArrayList<>();
        for (String line : ArqPerg) {
            if (!line.contains(perg)) {
                novasPerguntas.add(line);  // Adiciona apenas as perguntas que não contêm o número digitado.
            }
        }

        // Reescrevendo o arquivo com as perguntas restantes
        try (FileWriter fileWriter = new FileWriter(pathIn, false)) {
            for (String pergunta : novasPerguntas) {
                fileWriter.write(pergunta + "\r\n");  // Escreve cada pergunta no arquivo.
            }
            System.out.println("Pergunta deletada com sucesso!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void psqUsuario() throws IOException {
        String pathIn = "C:/Users/tiago/Desktop/JAVA/exercicios/Sistemas/Sistema de Cadastro/arquivos";

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

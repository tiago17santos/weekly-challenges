package com.sistema_cadastro.service;

import com.sistema_cadastro.dominio.Pergunta;
import com.sistema_cadastro.dominio.Pessoa;
import com.sistema_cadastro.repository.CadastroRepository;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CadastroServiceTest {

    @Test
    void listarPergunta_DeveExibirPerguntasNoConsole() {
        try (MockedStatic<CadastroRepository> mockedRepo = Mockito.mockStatic(CadastroRepository.class)) {
            List<Pergunta> perguntas = Arrays.asList(new Pergunta("Qual o seu nome?"),
                    new Pergunta("Qual a sua idade?"));

            mockedRepo.when(CadastroRepository::findAllPerguntas).thenReturn(perguntas);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            System.setOut(new PrintStream(baos));

            CadastroService.listarPergunta();

            String output = baos.toString();
            assertTrue(output.contains("Qual o seu nome?"));
            assertTrue(output.contains("Qual a sua idade?"));
        }
    }

    @Test
    void verificaEmail_DeveRetornarTrue_SeEmailJaCadastrado() {
        String emailTeste = "tiago@email.com";

        try (MockedStatic<CadastroRepository> mockedRepo = Mockito.mockStatic(CadastroRepository.class)) {
            // Simular o retorno do método estático
            mockedRepo.when(() -> CadastroRepository.verificaEmailCadastrado(emailTeste)).thenReturn(true);

            // Chamar o método e verificar o resultado
            boolean resultado = CadastroService.verificaEmail(emailTeste);
            assertTrue(resultado);
        }
    }

    @Test
    void verificaEmail_DeveRetornarFalse_SeEmailNaoCadastrado() {
        String emailTeste = "nao_existe@email.com";

        try (MockedStatic<CadastroRepository> mockedRepo = Mockito.mockStatic(CadastroRepository.class)) {
            mockedRepo.when(() -> CadastroRepository.verificaEmailCadastrado(emailTeste)).thenReturn(false);

            boolean resultado = CadastroService.verificaEmail(emailTeste);
            assertFalse(resultado);
        }
    }

    @Test
    void cadastrarPessoa_DeveSalvarPessoa() {
        Pessoa pessoa = Pessoa.PessoaBuilder.builder()
                .nome("joao")
                .email("joao@email.com")
                .idade(25)
                .altura(1.88)
                .build();

        try (MockedStatic<CadastroRepository> mockedRepo = Mockito.mockStatic(CadastroRepository.class)) {
            // Usando doNothing() para um método void
            mockedRepo.when(() -> CadastroRepository.insertPessoas(pessoa)).thenReturn(null); // ou .thenAnswer(invocation -> null);

            // Chama o método que invoca o método estático
            CadastroService.cadastrarPessoas();

            // Verifica se o método estático foi chamado corretamente
            mockedRepo.verify(() -> CadastroRepository.insertPessoas(pessoa), times(1));
        }catch (Exception e) {
            fail("O teste falhou com a exceção: " + e.getMessage());
        }
    }

    @Test
    void cadastrarPessoa_DeveFalhar_SeEmailJaExistir() {
        // Dados para o teste
        String nome = "Tiago Alberto";
        String email = "tiago@gmail.com";
        int idade = 25;
        double altura = 1.65;


        // Usando Mockito para mockar o método estático de verificação de email
        try (MockedStatic<CadastroRepository> mockedRepo = mockStatic(CadastroRepository.class)) {

            // Simula que o email JÁ está cadastrado
            mockedRepo.when(() -> CadastroRepository.verificaEmailCadastrado(email)).thenReturn(true);

            // Mockando o Scanner para simular as entradas
            Scanner scannerMock = mock(Scanner.class);
            when(scannerMock.nextLine()).thenReturn(nome, email);  // Simulando a entrada de nome e email
            when(scannerMock.nextInt()).thenReturn(idade);  // Simulando a entrada da idade
            when(scannerMock.nextDouble()).thenReturn(altura);  // Simulando a entrada da altura

            // Substituindo o Scanner no CadastroService
            CadastroService.sc = scannerMock;

            // Verificando que a exceção é lançada quando o email já existe
            CadastroService.CadastroException exception = assertThrows(CadastroService.CadastroException.class, CadastroService::cadastrarPessoas);

            // Verificando a mensagem da exceção
            assertEquals("Esse e-mail já existe, inclua outro.", exception.getMessage());
        }
    }

    @Test
    void cadastrarPessoa_DeveLancarExcecao_SeErroNoBanco() {
        // Dados para o teste
        String nome = "Tiago Alberto";
        String email = "tiago@gmail.com";
        int idade = 25;
        double altura = 1.65;


        // Mocking do CadastroRepository para simular erro ao inserir no banco
        try (MockedStatic<CadastroRepository> mockedRepo = mockStatic(CadastroRepository.class)) {
            // Simulando que ao tentar inserir no banco, ocorre uma exceção
            mockedRepo.when(() -> CadastroRepository.insertPessoas(any(Pessoa.class)))
                    .thenThrow(new RuntimeException("Erro ao inserir no banco"));

            // Mockando o Scanner para simular as entradas
            Scanner scannerMock = mock(Scanner.class);
            when(scannerMock.nextLine()).thenReturn(nome, email);  // Simulando a entrada de nome e email
            when(scannerMock.nextInt()).thenReturn(idade);  // Simulando a entrada da idade
            when(scannerMock.nextDouble()).thenReturn(altura);  // Simulando a entrada da altura

            // Substituindo o Scanner no CadastroService
            CadastroService.sc = scannerMock;

            // Verificando que a exceção é lançada quando há erro no banco
            RuntimeException exception = assertThrows(RuntimeException.class, CadastroService::cadastrarPessoas);

            // Verificando a mensagem da exceção
            assertEquals("Erro ao inserir no banco", exception.getMessage());
        }
    }

    @Test
    void listarPessoas_DeveExibirPessoasNoConsole() {

        List<Pessoa> pessoasMockadas  = List.of(
            Pessoa.PessoaBuilder.builder().id(1).nome("João").email("joao@email.com").idade(25).altura(1.88).build(),
            Pessoa.PessoaBuilder.builder().id(2).nome("Letícia").email("leticia@email.com").idade(45).altura(1.56).build()
        );

        // Mockando o comportamento do repositório para retornar essa lista
        try (MockedStatic<CadastroRepository> mockRepositorio = Mockito.mockStatic(CadastroRepository.class)) {
            mockRepositorio.when(CadastroRepository::findAllPessoa).thenReturn(pessoasMockadas);

            // Capturando saída do console
            ByteArrayOutputStream saidaCapturada = new ByteArrayOutputStream();
            System.setOut(new PrintStream(saidaCapturada));

            // Executando o método que imprime a lista de pessoas
            CadastroService.listarPessoas();

            // Restaurando saída original do console
            System.setOut(System.out);

            // Convertendo saída capturada para string
            String resultado = saidaCapturada.toString();

            // Verificando se o output contém os dados esperados
            List<String> linhasEsperadas = List.of("1 - João", "2 - Letícia");

            for (String linha : linhasEsperadas) {
                assertTrue(resultado.contains(linha), "A saída não contém: " + linha);
            }
        }
    }

    @Test
    void cadastrarPergunta_DeveSalvarPergunta() {
        String pergunta = "Qual o seu nome?";
        InputStream originalSystemIn = System.in;
        ByteArrayInputStream mockInput = new ByteArrayInputStream(pergunta.getBytes());
        System.setIn(mockInput); // Simula entrada do usuário

        try (MockedStatic<CadastroRepository> mockedRepo = Mockito.mockStatic(CadastroRepository.class)) {
            mockedRepo.when(() -> CadastroRepository.insertPerguntas(pergunta)).thenCallRealMethod();

            CadastroService.cadastrarPergunta();

            mockedRepo.verify(() -> CadastroRepository.insertPerguntas(pergunta), times(1));
        } finally {
            System.setIn(originalSystemIn); // Restaura entrada original
        }
    }

    @Test
    void deletarPergunta_DeveFalhar_CasoSejaAs4Primeiras() {
        int id = 3;

        String inputSimulado = id + "\n";  // Coloca o inteiro seguido de uma nova linha
        InputStream originalSystemIn = System.in;
        ByteArrayInputStream mockInput = new ByteArrayInputStream(inputSimulado.getBytes());
        System.setIn(mockInput); // Simula a entrada do usuário

        // Testa se a exceção será lançada
        // Deve lançar a exceção para os 4 primeiros IDs
        assertThrows(RuntimeException.class, CadastroService::deletarPergunta);

        System.setIn(originalSystemIn);  // Restaura a entrada original após o teste
    }

    @Test
    void deletarPergunta_DeveDeletarPerguntaTirandoAs4Primeiras() {
        int id = 5;

        String inputSimulado = id + "\n";  // Coloca o inteiro seguido de uma nova linha
        InputStream originalSystemIn = System.in;
        ByteArrayInputStream mockInput = new ByteArrayInputStream(inputSimulado.getBytes());
        System.setIn(mockInput); // Simula a entrada do usuário

        try (MockedStatic<CadastroRepository> mockedRepo = Mockito.mockStatic(CadastroRepository.class)) {
            mockedRepo.when(() -> CadastroRepository.deletePergunta(id)).thenCallRealMethod();

            CadastroService.deletarPergunta();

            mockedRepo.verify(() -> CadastroRepository.deletePergunta(id), times(1));
        } finally {
            System.setIn(originalSystemIn); // Restaura entrada original
        }

    }
}
package com.sistema_cadastro_api.Aservice;

import com.sistema_cadastro_api.models.PerguntaModel;
import com.sistema_cadastro_api.repositories.PerguntaRepository;
import com.sistema_cadastro_api.service.PerguntaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class PerguntaServiceTest {

    @Mock
    private PerguntaRepository perguntaRepository;  // Dependência do serviço que vamos simular.

    @InjectMocks
    private PerguntaService perguntaService;  // O serviço que estamos testando.

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);  // Inicializa os mocks antes de cada teste.
    }


    @Test
    void listarPergunta_DeveExibirPerguntasNoConsole() {
        // Setup: Criando uma lista simulada de PerguntaModel
        PerguntaModel pergunta1 = new PerguntaModel(1L, "Qual é sua cor favorita?");
        PerguntaModel pergunta2 = new PerguntaModel(2L, "Onde você mora?");
        List<PerguntaModel> perguntas = Arrays.asList(pergunta1, pergunta2);

        // Simulando o comportamento do repositório
        when(perguntaRepository.findAll()).thenReturn(perguntas);

        // Executando o método do serviço
        List<PerguntaModel> resultado = perguntaService.findAll();

        // Verificando se a lista retornada tem o tamanho correto e se contém as perguntas esperadas
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Qual é sua cor favorita?", resultado.get(0).getDescricao());
        assertEquals("Onde você mora?", resultado.get(1).getDescricao());

        // Verificando se o método findAll do repositório foi chamado
        verify(perguntaRepository, times(1)).findAll();
    }
}

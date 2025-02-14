package com.techverse.inflex_gestao_funcionarios.controllers;

import com.techverse.inflex_gestao_funcionarios.MainApp;
import com.techverse.inflex_gestao_funcionarios.entities.Funcionario;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ImpressaoController {

    @FXML
    private ComboBox<String> comboBoxImpressao;
    @FXML
    private TableView<Funcionario> tabelaImpressao;
    @FXML
    private TableColumn<Funcionario, String> colunaNomeImpressao;
    @FXML
    private TableColumn<Funcionario, String> colunaFuncaoImpressao;
    @FXML
    private TableColumn<Funcionario, String> colunaSalarioImpressao;

    public void initialize() {
        // Carregar as opções do ComboBox
        comboBoxImpressao.setItems(FXCollections.observableArrayList(
                "Todos Funcionários",
                "Agrupados por Função",
                "Aniversariantes",
                "Mais Velho",
                "Ordem Alfabética",
                "Total dos Salários",
                "Quantos SM Recebem"
        ));

        // Definir o comportamento da ComboBox (o que acontece quando uma opção é selecionada)
        comboBoxImpressao.setOnAction(event -> handleImpressaoSelecionada());

        // Definir o comportamento das colunas da tabela
        colunaNomeImpressao.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNome()));
        colunaFuncaoImpressao.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCargo()));
        colunaSalarioImpressao.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSalario().toString()));
    }

    @FXML
    private void handleImpressaoSelecionada() {
        // Obter a opção selecionada no ComboBox
        String opcao = comboBoxImpressao.getValue();

        switch (opcao) {
            case "Todos Funcionários":
                exibirTodosFuncionarios();
                break;
            case "Agrupados por Função":
                exibirAgrupadosPorFuncao();
                break;
            case "Aniversariantes":
                exibirAniversariantes();
                break;
            case "Mais Velho":
                exibirMaisVelho();
                break;
            case "Ordem Alfabética":
                exibirOrdemAlfabetica();
                break;
            case "Total dos Salários":
                exibirTotalSalarios();
                break;
            case "Quantos SM Recebem":
                exibirQuantosSM();
                break;
            default:
                break;
        }
    }

    // Exemplo de método para exibir todos os funcionários
    private void exibirTodosFuncionarios() {
        ObservableList<Funcionario> funcionarios = FXCollections.observableArrayList(getListaFuncionarios());
        tabelaImpressao.setItems(funcionarios);
    }

    private List<Funcionario> getListaFuncionarios() {
        // Aqui você pode recuperar a lista de funcionários de sua base de dados ou serviço
        return List.of(new Funcionario("Maria", LocalDate.of(2025, 2, 12), new BigDecimal("2500.00"), "Analista"),
                new Funcionario("João", LocalDate.of(2005, 2, 12), new BigDecimal("3500.00"), "Desenvolvedor"));
    }

    // Exemplo de método para exibir funcionários agrupados por função (isso seria mais complexo, com agrupamento)
    private void exibirAgrupadosPorFuncao() {
        // Lógica para exibir os funcionários agrupados por função
    }


    @FXML
    public void exibirAniversariantes() {

    }

    @FXML
    public void exibirMaisVelho() {

    }

    @FXML
    public void exibirOrdemAlfabetica() {

    }

    @FXML
    public void exibirTotalSalarios() {

    }

    @FXML
    public void exibirQuantosSM() {

    }

    @FXML
    public void handleVoltarMenu() throws IOException {
        MainApp.setRoot("menu-view");
    }

    @FXML
    public void handleLimparLista() {

    }
}

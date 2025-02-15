package com.techverse.inflex_gestao_funcionarios.controllers;

import com.techverse.inflex_gestao_funcionarios.MainApp;
import com.techverse.inflex_gestao_funcionarios.entities.Funcionario;
import com.techverse.inflex_gestao_funcionarios.services.FuncionarioRelatorioService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;

public class ImpressaoController {

    @FXML
    private ComboBox<String> comboBoxImpressao;

    @FXML
    private TableView<Funcionario> tabelaImpressao;

    @FXML
    private TableColumn<Funcionario, String> nomeColumn;

    @FXML
    private TableColumn<Funcionario, String> cargoColumn;

    @FXML
    private TableColumn<Funcionario, String> salarioColumn;

    @FXML
    private TableColumn<Funcionario, String> nascimentoColumn;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); //Formatar a data
    DecimalFormat decimalFormat = new DecimalFormat("###,###.##"); //Formatar salário

    FuncionarioRelatorioService funcionarioRelatorioService;

    public void initialize() {
        funcionarioRelatorioService = new FuncionarioRelatorioService();
        comboBoxsetItems();

        // Definir o comportamento da ComboBox (o que acontece quando uma opção é selecionada)
        comboBoxImpressao.setOnAction(event -> handleImpressaoSelecionada());

        inicializarTabela();
        tabelaImpressao.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); // ajustar colunas automaticamente ao espaço disponível
    }

    private void inicializarTabela() {
        nomeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNome()));
        nascimentoColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
                formatter.format(cellData.getValue().getDataNascimento())
        ));
        salarioColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
                decimalFormat.format(cellData.getValue().getSalario())
        ));
        cargoColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCargo()));


    }

    // Carregar as opções do ComboBox
    private void comboBoxsetItems() {
        comboBoxImpressao.setItems(FXCollections.observableArrayList(
                "Selecione..",
                "Todos Funcionários",
                "Agrupados por Função",
                "Aniversariantes",
                "Mais Velho",
                "Ordem Alfabética",
                "Total dos Salários",
                "Quantos SM Recebem Cada"
        ));
        comboBoxImpressao.setValue("Selecione..");
    }

    @FXML
    private void handleImpressaoSelecionada() {
        // Obter a opção selecionada no ComboBox
        String opcao = comboBoxImpressao.getValue();

        switch (opcao) {
            case "Selecione..":
                handleLimparLista();
                break;
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
            case "Quantos SM Recebem Cada":
                exibirQuantosSM();
                break;
            default:
                break;
        }
    }


    private void exibirTodosFuncionarios() {
        ObservableList<Funcionario> funcionarios = funcionarioRelatorioService.listarFuncionarios();
        funcionarioRelatorioService.formatarCampos();
        tabelaImpressao.setItems(funcionarios);
    }


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
        tabelaImpressao.getItems().clear();
        comboBoxImpressao.setValue("Selecione..");
    }
}

package com.techverse.inflex_gestao_funcionarios.controllers;

import com.techverse.inflex_gestao_funcionarios.MainApp;
import com.techverse.inflex_gestao_funcionarios.entities.Funcionario;
import com.techverse.inflex_gestao_funcionarios.services.FuncionarioRelatorioService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.math.BigDecimal;
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

    @FXML
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

    @FXML
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
                setarVisibilidade(true, true, true, true);
                salarioColumn.setText("Sálario");
                break;
            case "Todos Funcionários":
                exibirTodosFuncionarios();
                setarVisibilidade(true, true, true, true);
                salarioColumn.setText("Sálario");
                break;
            case "Agrupados por Função":
                exibirAgrupadosPorFuncao();
                setarVisibilidade(true, true, false, false);
                break;
            case "Aniversariantes":
                exibirAniversariantes();
                setarVisibilidade(true, true, true, true);
                salarioColumn.setText("Sálario");
                break;
            case "Mais Velho":
                exibirFuncMaisVelho();
                setarVisibilidade(true, false, false, true);
                break;
            case "Ordem Alfabética":
                exibirFuncOrdemAlfabetica();
                setarVisibilidade(true, false, false, true);
                break;
            case "Total dos Salários":
                exibirTotalSalarios();
                setarVisibilidade(false, false, true, false);
                break;
            case "Quantos SM Recebem Cada":
                exibirQuantosSMRecebem();
                setarVisibilidade(true, true, true, false);
                salarioColumn.setText("Sálario");
                break;
            default:
                break;
        }
        // Após atualizar a tabela, forçar o ajuste das colunas
        tabelaImpressao.layout();
        tabelaImpressao.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); // Força o ajuste do layout
        
    }

    @FXML
    public void setarVisibilidade(Boolean nome, Boolean cargo, Boolean salario, Boolean nascimento) {
        nomeColumn.setVisible(nome);
        cargoColumn.setVisible(cargo);
        salarioColumn.setVisible(salario);
        nascimentoColumn.setVisible(nascimento);
    }


    @FXML
    private void exibirTodosFuncionarios() {
        tabelaImpressao.setItems(funcionarioRelatorioService.listarFuncionarios());
    }

    @FXML
    private void exibirAgrupadosPorFuncao() {
        tabelaImpressao.setItems(funcionarioRelatorioService.listarPorFuncao());
    }

    @FXML
    public void exibirAniversariantes() {
        tabelaImpressao.setItems(funcionarioRelatorioService.listarAniversariantes());
    }

    @FXML
    public void exibirFuncMaisVelho() {
        tabelaImpressao.setItems(funcionarioRelatorioService.listarFuncMaisVelho());
    }

    @FXML
    public void exibirFuncOrdemAlfabetica() {
        tabelaImpressao.setItems(funcionarioRelatorioService.listarFuncOrdemAlbetica());
    }

    @FXML
    public void exibirTotalSalarios() {
        BigDecimal totalSalarios = funcionarioRelatorioService.listarTotalSalarios();
        tabelaImpressao.getItems().clear();  // Limpar itens da tabela para mostrar o salário no titulo
        salarioColumn.setText("Total dos Salários: R$" + decimalFormat.format(totalSalarios));
    }

    @FXML
    public void exibirQuantosSMRecebem() {
        tabelaImpressao.setItems(funcionarioRelatorioService.listarQuantosSMRecebem());
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

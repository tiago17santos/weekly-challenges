package com.techverse.inflex_gestao_funcionarios.controllers;

import com.techverse.inflex_gestao_funcionarios.MainApp;
import com.techverse.inflex_gestao_funcionarios.entities.Funcionario;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.converter.BigDecimalStringConverter;

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
    private TableColumn<Funcionario, String> nomeColumn;

    @FXML
    private TableColumn<Funcionario, String> cargoColumn;

    @FXML
    private TableColumn<Funcionario, BigDecimal> salarioColumn;

    @FXML
    private TableColumn<Funcionario, LocalDate> nascimentoColumn;

    public void initialize() {
        comboBoxsetItems();

        // Definir o comportamento da ComboBox (o que acontece quando uma opção é selecionada)
        comboBoxImpressao.setOnAction(event -> handleImpressaoSelecionada());

        inicializarTabela();
        tabelaImpressao.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); // ajustar colunas automaticamente ao espaço disponível
    }

    private void inicializarTabela(){
        nomeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNome()));
        nascimentoColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getDataNascimento()));
        cargoColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCargo()));
        salarioColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getSalario()));

    }

    // Carregar as opções do ComboBox
    private void comboBoxsetItems(){
        comboBoxImpressao.setItems(FXCollections.observableArrayList(
                "Todos Funcionários",
                "Agrupados por Função",
                "Aniversariantes",
                "Mais Velho",
                "Ordem Alfabética",
                "Total dos Salários",
                "Quantos SM Recebem Cada"
        ));
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
            case "Quantos SM Recebem Cada":
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

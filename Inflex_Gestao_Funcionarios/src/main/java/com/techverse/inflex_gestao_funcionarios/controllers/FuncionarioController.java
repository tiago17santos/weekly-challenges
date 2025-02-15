package com.techverse.inflex_gestao_funcionarios.controllers;

import com.techverse.inflex_gestao_funcionarios.MainApp;
import com.techverse.inflex_gestao_funcionarios.entities.Funcionario;
import com.techverse.inflex_gestao_funcionarios.services.FuncionarioService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

public class FuncionarioController {

    @FXML
    private Button btnImportarFunc;

    @FXML
    private Button btnAplicarAumento;

    @FXML
    private Button btnRemoverFunc;

    @FXML
    private Button btnVoltar;

    @FXML
    private TableView<Funcionario> tabelaFuncionarios;

    @FXML
    private TableColumn<Funcionario, String> nomeColumn;

    @FXML
    private TableColumn<Funcionario, String> cargoColumn;

    @FXML
    private TableColumn<Funcionario, BigDecimal> salarioColumn;

    @FXML
    private TableColumn<Funcionario, LocalDate> nascimentoColumn;

    private FuncionarioService funcionarioService;

    private final ObservableList<Funcionario> listaFuncionarios = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        funcionarioService = new FuncionarioService();
        inicializarTabela();
        tabelaFuncionarios.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }


    @FXML
    public void inicializarTabela() {
        ObservableList<Funcionario> funcionariosList = funcionarioService.carregarFuncionarios();

        // Definir como as colunas serão populadas
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        nascimentoColumn.setCellValueFactory(new PropertyValueFactory<>("dataNascimento"));
        salarioColumn.setCellValueFactory(new PropertyValueFactory<>("salario"));
        cargoColumn.setCellValueFactory(new PropertyValueFactory<>("cargo"));

        // Adicionar os dados à tabela
        tabelaFuncionarios.setItems(funcionariosList);
    }


    @FXML
    public void importarFuncionarios() {
        // Abre o explorador de arquivos para a escolha do arquivo
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("."));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File selectFile = fileChooser.showOpenDialog(btnImportarFunc.getScene().getWindow());

        if (selectFile != null) {
            try (Scanner scanner = new Scanner(selectFile)) {
                listaFuncionarios.clear();  // Limpa a lista antes de adicionar novos dados

                if (scanner.hasNextLine()) { // Ignora a linha do cabeçalho (caso tenha)
                    scanner.nextLine();
                }

                boolean arquivoValido = true; // Flag para indicar se o arquivo está no formato correto

                // Lê o arquivo linha por linha e valida cada linha
                while (scanner.hasNextLine()) {
                    String linha = scanner.nextLine().trim();

                    if (linha.isEmpty()) { // Se a linha estiver vazia, pula para a próxima
                        continue;
                    }

                    String[] dados = linha.split(",");

                    // Verifica se a linha tem exatamente 4 colunas (nome,data_nascimento,salario,cargo)
                    if (dados.length != 4) {
                        exibirAlerta("error", "Erro de Formato", "Formato Incorreto", "A linha não está no formato esperado. \nCada linha deve ter 'nome,data_nascimento,salario,cargo'. \nLinha ignorada: " + linha);
                        arquivoValido = false;
                        throw new Exception("Linha vazia encontrada. Interrompendo processamento.");
                    }

                    try {
                        String nome = dados[0];
                        String[] dataParts = dados[1].split("/");

                        if (dataParts.length != 3) {
                            exibirAlerta("error", "Erro de Data", "Formato de Data Inválido", "Data inválida na linha: " + linha);
                            arquivoValido = false;
                            throw new Exception("Data inválida. Interrompendo processamento.");
                        }

                        int dia = Integer.parseInt(dataParts[0]);
                        int mes = Integer.parseInt(dataParts[1]);
                        int ano = Integer.parseInt(dataParts[2]);

                        LocalDate dtNasc = LocalDate.of(ano, mes, dia);
                        BigDecimal salario = new BigDecimal(dados[2]);
                        String cargo = dados[3];

                        if (mes < 1 || mes > 12 || dia < 1 || dia > 31) {
                            exibirAlerta("error", "Erro de Data", "Data Inválida", "Data inválida na linha: " + linha);
                            arquivoValido = false;
                            throw new Exception("Data inválida. Interrompendo processamento.");
                        }

                        if (salario.compareTo(BigDecimal.ZERO) <= 0) {
                            exibirAlerta("error", "Erro de Salário", "Salário Inválido", "Salário inválido na linha: " + linha);
                            arquivoValido = false;
                            throw new Exception("Salário inválido. Interrompendo processamento.");
                        }

                        Funcionario func = new Funcionario(nome, dtNasc, salario, cargo);
                        listaFuncionarios.add(func);

                    } catch (Exception e) {
                        exibirAlerta("error", "Erro ao Processar Linha", "Erro de Dados", "Erro ao processar dados da linha: " + linha);
                        arquivoValido = false;
                    }
                }

                // Atualiza a tabela com a lista de funcionários
                ObservableList<Funcionario> funcionariosObservableList = FXCollections.observableArrayList(listaFuncionarios);
                tabelaFuncionarios.setItems(funcionariosObservableList);

                if (arquivoValido) {
                    funcionarioService.salvarFuncionarios();
                } else {
                    listaFuncionarios.clear();
                    exibirAlerta("error", "Erro no Arquivo", "Arquivo Inválido", "O arquivo contém erro(s) de formatação. Nenhum aluno foi adicionado.");
                }

                if (listaFuncionarios.isEmpty()) {
                    exibirAlerta("alerta", "Aviso", "Nenhum Aluno Encontrado", "O arquivo CSV não contém alunos válidos.");
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                exibirAlerta("error", "Erro no Arquivo", "Arquivo Inválido", "O arquivo contém erro(s). Nenhum aluno foi adicionado.");
            }
        }

    }

    @FXML
    public void removerFuncionario() {
        int selectedIndex = tabelaFuncionarios.getSelectionModel().getSelectedIndex();
        Funcionario func = tabelaFuncionarios.getSelectionModel().getSelectedItem();

        if (selectedIndex >= 0) {
            funcionarioService.excluirFuncionario(selectedIndex);
            tabelaFuncionarios.getSelectionModel().clearSelection();
            tabelaFuncionarios.getItems().remove(selectedIndex);
            exibirAlerta("sucesso", "Removido", "Funcionário Removido", "Funcionário removido com sucesso.");
        } else {
            exibirAlerta("error", "Erro na remoção", "Nenhum registro seleionado", "Selecione a linha de registro do funcionário e tente novamente!");
        }
    }

    @FXML
    public void handleAplicarAumento() {
        funcionarioService.aplicarAumento();
    }

    @FXML
    public void voltarParaMenu() throws IOException {
        MainApp.setRoot("menu-view");
    }

    // Metodo genérico para exibir alertas
    public void exibirAlerta(String tipo, String titulo, String header, String mensagem) {
        Alert alert;

        if (tipo.equals("sucesso")) {
            alert = new Alert(Alert.AlertType.INFORMATION);
        } else if (tipo.equals("erro")) {
            alert = new Alert(Alert.AlertType.ERROR);
        } else {
            alert = new Alert(Alert.AlertType.WARNING);
        }
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}

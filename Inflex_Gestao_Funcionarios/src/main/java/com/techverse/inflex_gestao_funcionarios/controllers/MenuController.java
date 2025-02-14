package com.techverse.inflex_gestao_funcionarios.controllers;

import com.techverse.inflex_gestao_funcionarios.MainApp;
import com.techverse.inflex_gestao_funcionarios.services.FuncionarioService;
import javafx.fxml.FXML;

import java.io.IOException;

public class MenuController {

    private FuncionarioService funcionarioService;

    // Método setter para injetar o FuncionarioService
    public void setFuncionarioService(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @FXML
    public void handleAdicionarRemoverFuncionario() throws IOException {
        MainApp.setRoot("adicRemoverFunc-view");
    }

    @FXML
    public void handleAplicarAumento(){
        funcionarioService.aplicarAumento();
    }

    @FXML
    public void handleImpressao() throws IOException {
        MainApp.setRoot("impressoes-view");
    }

}

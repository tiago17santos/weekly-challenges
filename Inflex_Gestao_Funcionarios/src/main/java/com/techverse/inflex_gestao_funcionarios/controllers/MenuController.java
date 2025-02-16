package com.techverse.inflex_gestao_funcionarios.controllers;

import com.techverse.inflex_gestao_funcionarios.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class MenuController {
    @FXML
    private Button btnAdicionarRemoverFuncionario;

    @FXML
    private Button btnImpressao;

    @FXML
    public void handleAdicionarRemoverFuncionario() throws IOException {
        MainApp.setRoot("adicRemoverFunc-view");
    }

    @FXML
    public void handleImpressao() throws IOException {
        MainApp.setRoot("impressoes-view");
    }
}

package com.techverse.inflex_gestao_funcionarios.views;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class FuncionarioView {

    private VBox root;

    public FuncionarioView() {
        root = new VBox();
        Label titleLabel = new Label("Lista de Funcionários");
        Button addButton = new Button("Adicionar Funcionário");

        // Lógica de ação para o botão
        addButton.setOnAction(e -> {
            // Aqui você pode chamar o método para adicionar um funcionário
            System.out.println("Adicionar funcionário!");
        });

        root.getChildren().addAll(titleLabel, addButton);
    }

    public VBox getRoot() {
        return root;
    }
}

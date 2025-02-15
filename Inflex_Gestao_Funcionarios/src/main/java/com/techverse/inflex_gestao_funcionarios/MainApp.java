package com.techverse.inflex_gestao_funcionarios;

import com.techverse.inflex_gestao_funcionarios.controllers.MenuController;
import com.techverse.inflex_gestao_funcionarios.services.FuncionarioService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

    private static Scene scene;


    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("menu-view"), 600, 400);
        stage.setTitle("Gestão de Funcionários");
        stage.setScene(scene);
        stage.show();
    }
    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    //Metodo para carregar o arquivo FXML e garantir que retorna um Parent
    private static Parent loadFXML(String fxml) throws IOException {
        // Carrega o arquivo FXML
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource(fxml + ".fxml"));
        Parent root = fxmlLoader.load(); // Carrega a interface FXML

        // Obtém o controlador
        Object controller = fxmlLoader.getController();

        // Verifica se o controlador é o correto
        if (controller instanceof MenuController) {
            MenuController menuController = (MenuController) controller;
            FuncionarioService funcionarioService = new FuncionarioService(); // Criação do serviço
            menuController.setFuncionarioService(funcionarioService); // Injeção de dependência
        } else {
            // Lidar com outro controlador, se necessário
            System.out.println("O controlador não é do tipo esperado.");
        }

        // Retorna o nó raiz da interface FXML
        return root;
    }



    public static void main(String[] args) {
        launch();
    }
}
package com.techverse.inflex_gestao_funcionarios;

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
        return fxmlLoader.load();  // Isso garante que o nó raiz é do tipo Parent
    }

    public static void main(String[] args) {
        launch();
    }
}
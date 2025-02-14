module com.techverse.inflex_gestao_funcionarios {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires java.sql;


    opens com.techverse.inflex_gestao_funcionarios.entities to javafx.base;
    opens com.techverse.inflex_gestao_funcionarios to javafx.fxml;
    exports com.techverse.inflex_gestao_funcionarios;
    exports com.techverse.inflex_gestao_funcionarios.controllers;
    opens com.techverse.inflex_gestao_funcionarios.controllers to javafx.fxml;

}
package com.techverse.inflex_gestao_funcionarios.services;

import com.techverse.inflex_gestao_funcionarios.entities.Funcionario;
import javafx.collections.ObservableList;

public class FuncionarioRelatorioService {
    /*
    3.3 – Imprimir todos os funcionários com todas suas informações, sendo que:
    3.5 – Agrupar os funcionários por função em um MAP, sendo a chave a “função” e o valor a “lista de funcionários”.
    3.6 – Imprimir os funcionários, agrupados por função.
    3.8 – Imprimir os funcionários que fazem aniversário no mês 10 e 12.
    3.9 – Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade.
    3.10 – Imprimir a lista de funcionários por ordem alfabética.
    3.11 – Imprimir o total dos salários dos funcionários.
    3.12 – Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00.

    * */

    FuncionarioService funcionarioService;

    public FuncionarioRelatorioService() {
        funcionarioService = new FuncionarioService();
    }


    public ObservableList<Funcionario> listarFuncionarios() {
        return funcionarioService.carregarFuncionarios();
    }

}

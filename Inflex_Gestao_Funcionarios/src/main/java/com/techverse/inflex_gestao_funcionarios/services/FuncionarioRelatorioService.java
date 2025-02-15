package com.techverse.inflex_gestao_funcionarios.services;

import com.techverse.inflex_gestao_funcionarios.entities.Funcionario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.math.BigDecimal;
import java.time.Month;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FuncionarioRelatorioService {
    /*
    3.3 – Imprimir todos os funcionários com todas suas informações, sendo que:
        • informação de data deve ser exibido no formato dd/mm/aaaa;
        • informação de valor numérico deve ser exibida no formatado com separador de milhar como ponto e decimal como vírgula.
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

    public ObservableList<Funcionario> listarPorFuncao(){

        Map<String, List<Funcionario>> funcionariosPorCargo = funcionarioService.carregarFuncionarios().stream()
                .collect(Collectors.groupingBy(Funcionario::getCargo));

        ObservableList<Funcionario> funcionariosDesagrupados = FXCollections.observableArrayList();

        funcionariosPorCargo.forEach((cargo, listaFuncionarios) -> {
            listaFuncionarios.forEach(funcionario -> {
                funcionariosDesagrupados.add(new Funcionario(funcionario.getNome(), funcionario.getDataNascimento(), funcionario.getSalario(), cargo));
            });
        });

        return funcionariosDesagrupados;
    }

    public ObservableList<Funcionario> listarAniversariantes(){
        List<Funcionario> aniversariantes = funcionarioService.carregarFuncionarios().stream()
                .filter(func -> func.getDataNascimento().getMonth().equals(Month.OCTOBER) || func.getDataNascimento().getMonth().equals(Month.DECEMBER))
                .collect(Collectors.toList());

        return FXCollections.observableArrayList(aniversariantes);
    }

    public ObservableList<Funcionario> listarFuncMaisVelho(){
        Funcionario maisVelho = funcionarioService.carregarFuncionarios().stream()
                .min(Comparator.comparing(Funcionario::getDataNascimento)) // Metodo min para comparar datas e pegar mais antigas(retorna o funcionario mais velho)
                .orElse(null); // Retorna null caso não haja funcionários

        return FXCollections.observableArrayList(maisVelho);
    }

    public ObservableList<Funcionario> listarFuncOrdemAlbetica(){
        List<Funcionario> funcionarios = funcionarioService.carregarFuncionarios().stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .toList();
        return FXCollections.observableArrayList(funcionarios);
    }

    /*public ObservableList<BigDecimal> listarTotalSalarios() {
        BigDecimal totalSalario = funcionarioService.carregarFuncionarios().stream()
                .map(Funcionario::getSalario) // Obtemos os salários dos funcionários
                .reduce(BigDecimal.ZERO, BigDecimal::add); // Soma todos os salários

        return FXCollections.observableArrayList(totalSalario);
    }*/

    public ObservableList<Funcionario> listarQuantosSMRecebem(){
        BigDecimal salarioMinimo = BigDecimal.valueOf(1212.00);

        List<Funcionario> funcionarios = funcionarioService.carregarFuncionarios().stream()
                .map(func -> {
                    BigDecimal salarioPorSM = func.getSalario().divide(salarioMinimo, 2, BigDecimal.ROUND_HALF_UP);
                    func.setSalario(salarioPorSM); // Atualiza o salário do funcionário com o valor calculado
                    return func;
                })
                .toList();

        return FXCollections.observableArrayList(funcionarios);
    }

}

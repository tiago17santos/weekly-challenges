package com.techverse.inflex_gestao_funcionarios.services;


import com.techverse.inflex_gestao_funcionarios.entities.Funcionario;

import java.math.BigDecimal;
import java.util.HashMap;

public class FuncionarioService {
    /*
     3.1 – Inserir todos os funcionários, na mesma ordem e informações da tabela acima.
     3.2 – Remover o funcionário “João” da lista.
     3.4 – Os funcionários receberam 10% de aumento de salário, atualizar a lista de funcionários com novo valor.
     */

    private HashMap<Integer, Funcionario> listaFuncionarios = new HashMap<>();

    public void aplicarAumento() {
        BigDecimal salario = listaFuncionarios.get(listaFuncionarios.size() - 1).getSalario();
        //salario = salario * 0.10;
    }

    public void inserirFuncionario(Funcionario funcionario) {
        int id = listaFuncionarios.size();

        if (id == 0) {
            listaFuncionarios.put(1, funcionario);
        } else {
            listaFuncionarios.put(id, funcionario);
        }
    }

    public void excluirFuncionario(int id) {
        listaFuncionarios.remove(id);
    }

    public HashMap<Integer, Funcionario> getFuncionarios() {
        return listaFuncionarios;
    }


}

package com.techverse.inflex_gestao_funcionarios.services;


import com.techverse.inflex_gestao_funcionarios.entities.Funcionario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;

public class FuncionarioService {
    /*
     3.1 – Inserir todos os funcionários, na mesma ordem e informações da tabela acima.
     3.2 – Remover o funcionário “João” da lista.
     3.4 – Os funcionários receberam 10% de aumento de salário, atualizar a lista de funcionários com novo valor.
     */

    private static FuncionarioService instancia;
    private HashMap<Integer, Funcionario> listaFuncionarios = new HashMap<>();

    public FuncionarioService() {
    }

    // Metodo para obter a instância única
    public static FuncionarioService getInstance() {
        if (instancia == null) {
            instancia = new FuncionarioService();
        }
        return instancia;
    }

    public void aplicarAumento() {
        double percentual = 10.0;
        BigDecimal percentualBigDecimal = new BigDecimal(percentual / 100);  // Converte o percentual para BigDecimal

        listaFuncionarios.values().forEach(f -> {
            System.out.println(f.getNome());
        });

        listaFuncionarios.values().forEach(funcionario -> {
            BigDecimal salarioAtual = funcionario.getSalario();

            // Calcula o novo salário
            BigDecimal aumento = BigDecimal.ONE.add(percentualBigDecimal); // 1 + percentual
            BigDecimal novoSalario = salarioAtual.multiply(aumento).setScale(2, RoundingMode.HALF_UP); // Multiplica e arredonda o valor

            funcionario.setSalario(novoSalario); // Define o novo salário
        });


    }

    public void salvarFuncionarios() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("C:\\Users\\tiago\\Desktop\\func.obj"))) {
            out.writeObject(listaFuncionarios);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Funcionario> carregarFuncionarios() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("C:\\Users\\tiago\\Desktop\\func.obj"))) {
            listaFuncionarios = (HashMap<Integer, Funcionario>) in.readObject();
        } catch (EOFException e) { // Se o arquivo estiver vazio ou se o final do arquivo for atingido
            return FXCollections.observableArrayList();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return FXCollections.observableArrayList(listaFuncionarios.values());
    }

    public void excluirFuncionario(int id) {

        if (listaFuncionarios.containsKey(id)){
            listaFuncionarios.remove(id);
        }
        salvarFuncionarios();
    }

    public HashMap<Integer, Funcionario> getFuncionarios() {
        listaFuncionarios.values().forEach(f -> System.out.println(f.getNome()));
        return listaFuncionarios;
    }


}

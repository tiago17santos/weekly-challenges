package com.techverse.inflex_gestao_funcionarios.services;


import com.techverse.inflex_gestao_funcionarios.entities.Funcionario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;

public class FuncionarioService {
    private final String diretorio = FuncionarioService.class.getResource("/com/techverse/inflex_gestao_funcionarios/arquivoSerial/funcionarios.obj").getPath();

    private HashMap<Integer, Funcionario> listaFuncionarios = new HashMap<>();

    public FuncionarioService() {
        carregarFuncionarios();
    }

    public ObservableList<Funcionario> carregarFuncionarios() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(diretorio))) {
            listaFuncionarios = (HashMap<Integer, Funcionario>) in.readObject();
        } catch (EOFException e) { // Se o arquivo estiver vazio ou se o final do arquivo for atingido
            return FXCollections.observableArrayList();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return FXCollections.observableArrayList(listaFuncionarios.values());
    }

    public void salvarFuncionarios(HashMap<Integer, Funcionario> funcionarios) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(diretorio))) {
            out.writeObject(funcionarios);
            out.flush(); //garantir que os dados sejam escritos no arquivo antes de terminar a execução
            carregarFuncionarios();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void excluirFuncionario(int id) {
        if (listaFuncionarios.containsKey(id)) {
            listaFuncionarios.remove(id);
        }
        listaFuncionarios.values().forEach(f -> System.out.println(f.getNome()));
        salvarFuncionarios(listaFuncionarios);
    }

    public void aplicarAumento() {
        double percentual = 10.0;
        BigDecimal percentualBigDecimal = new BigDecimal(percentual / 100);  // Converte o percentual para BigDecimal

        listaFuncionarios.values().forEach(funcionario -> {
            BigDecimal salarioAtual = funcionario.getSalario();

            // Calcula o novo salário
            BigDecimal aumento = BigDecimal.ONE.add(percentualBigDecimal); // 1 + percentual
            BigDecimal novoSalario = salarioAtual.multiply(aumento).setScale(2, RoundingMode.HALF_UP); // Multiplica e arredonda o valor

            funcionario.setSalario(novoSalario); // Define o novo salário
        });
        salvarFuncionarios(listaFuncionarios);
    }
}

package com.sistema_cadastro.repository;

import com.sistema_cadastro.conn.ConnectionFactory;
import com.sistema_cadastro.dominio.Pergunta;
import com.sistema_cadastro.dominio.Pessoa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CadastroRpository {

    public static void insertPessoas(Pessoa pessoa) {

        String sql = "INSERT INTO `sistema_cadastros`.`pessoas` (nome,email,idade,altura) VALUES (?, ?, ?, ?);";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, pessoa.getNome());
            ps.setString(2, pessoa.getEmail());
            ps.setInt(3, pessoa.getIdade());
            ps.setDouble(4, pessoa.getAltura());

            ps.executeUpdate();

            System.out.println("Pessoa inserida com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void insertPerguntas(String pergunta) {
        String sql = "INSERT INTO `sistema_cadastros`.`perguntas` (pergunta) VALUES (?);";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, pergunta);
            ps.executeUpdate();
            System.out.println("Pergunta inserida com sucesso!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Pergunta> findAllPerguntas() {
        String sql = "SELECT id,pergunta FROM `sistema_cadastros`.`perguntas`;";

        List<Pergunta> perguntas = new ArrayList<>();
        ResultSet rs = null;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            rs = ps.executeQuery(sql);

            while (rs.next()) {
                Pergunta perg = new Pergunta(rs.getString("pergunta"));
                perg.setId(rs.getInt("id"));
                perguntas.add(perg);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return perguntas;
    }



    public static List<Pessoa> findAllPessoa() {
        String sql = "SELECT id,nome FROM `sistema_cadastros`.`pessoas`;";

        List<Pessoa> pessoas = new ArrayList<>();
        ResultSet rs = null;

        try (Connection conn = ConnectionFactory.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)){
            rs = ps.executeQuery(sql);
            while (rs.next()) {
                Pessoa p = new Pessoa();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));

                pessoas.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return pessoas;
    }

    public static void deletarPergunta(int id) {
        String sql = "DELETE FROM `sistema_cadastros`.`perguntas` WHERE `id` = ?;";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Pergunta deletada com sucesso!");
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Pessoa> findPessoaByName(String nome){
        String sql = "SELECT id,nome FROM `sistema_cadastros`.`pessoas` WHERE nome like ?";

        List<Pessoa> pessoas = new ArrayList<>();
        ResultSet rs = null;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){
            String nomeParaBuscar = "%" + nome + "%";

            ps.setString(1,nomeParaBuscar);
            rs = ps.executeQuery();
            while (rs.next()) {
                Pessoa p = new Pessoa();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));

                pessoas.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return pessoas;

    }

    public static List<Pessoa> findPessoaByAge(int idade){
        String sql = "SELECT id,nome FROM `sistema_cadastros`.`pessoas` WHERE idade = ?";

        List<Pessoa> pessoas = new ArrayList<>();
        ResultSet rs = null;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setInt(1,idade);
            rs = ps.executeQuery();
            while (rs.next()) {
                Pessoa p = new Pessoa();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));

                pessoas.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return pessoas;

    }

    public static List<Pessoa> findPessoaByEmail(String email){
        String sql = "SELECT id,nome FROM `sistema_cadastros`.`pessoas` WHERE email like ?";

        List<Pessoa> pessoas = new ArrayList<>();
        ResultSet rs = null;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){

            String emailParaBuscar = "%" + email + "%";
            ps.setString(1,emailParaBuscar);
            rs = ps.executeQuery();

            while (rs.next()) {
                Pessoa p = new Pessoa();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));

                pessoas.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return pessoas;

    }
}

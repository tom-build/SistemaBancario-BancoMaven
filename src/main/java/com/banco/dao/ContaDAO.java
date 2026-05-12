package com.banco.dao;

import com.banco.model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContaDAO {

    private final Connection conn;

    public ContaDAO(Connection conn) {
        this.conn = conn;
    }

    public void salvar(ContaBancaria conta) {

        String sql = "INSERT INTO contas (nome, saldo, tipo) VALUES (?, ?, ?)";

        try (
                PreparedStatement ps = conn.prepareStatement(
                        sql,
                        Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, conta.getNome());
            ps.setDouble(2, conta.getSaldo());
            ps.setString(3, conta.getTipo());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                conta.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Erro ao salvar conta",
                    e);
        }
    }

    public List<ContaBancaria> listar() {

        List<ContaBancaria> lista = new ArrayList<>();

        String sql = "SELECT * FROM contas";

        try (
                PreparedStatement ps = conn.prepareStatement(sql);

                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                String tipo = rs.getString("tipo");

                ContaBancaria conta;

                if ("corrente"
                        .equalsIgnoreCase(tipo)) {

                    conta = new ContaCorrente(
                            rs.getString("nome"),
                            rs.getDouble("saldo"));

                } else {

                    conta = new ContaPoupanca(
                            rs.getString("nome"),
                            rs.getDouble("saldo"));
                }

                conta.setId(rs.getInt("id"));

                lista.add(conta);
            }

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Erro ao listar contas",
                    e);
        }

        return lista;
    }

    public void atualizarSaldo(
            int id,
            double novoSaldo) {

        String sql = "UPDATE contas SET saldo = ? WHERE id = ?";

        try (
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, novoSaldo);
            stmt.setInt(2, id);

            int linhas = stmt.executeUpdate();

            if (linhas == 0) {

                throw new RuntimeException(
                        "Conta não encontrada para atualização.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Erro ao atualizar saldo",
                    e);
        }
    }

    public ContaBancaria buscarPorId(int id) {

        String sql = "SELECT * FROM contas WHERE id = ?";

        try (
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (
                    ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    String tipo = rs.getString("tipo");

                    String nome = rs.getString("nome");

                    double saldo = rs.getDouble("saldo");

                    ContaBancaria conta;

                    if ("corrente"
                            .equalsIgnoreCase(tipo)) {

                        conta = new ContaCorrente(
                                nome,
                                saldo);

                    } else {

                        conta = new ContaPoupanca(
                                nome,
                                saldo);
                    }

                    conta.setId(id);

                    return conta;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Erro ao buscar conta",
                    e);
        }

        return null;
    }

    public void excluirConta(int id) {

        String sql = "DELETE FROM contas WHERE id = ?";

        try (
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas == 0) {

                throw new RuntimeException(
                        "Conta não encontrada.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Erro ao excluir conta",
                    e);
        }
    }
}
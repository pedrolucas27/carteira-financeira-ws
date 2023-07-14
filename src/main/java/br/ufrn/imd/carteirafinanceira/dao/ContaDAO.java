package br.ufrn.imd.carteirafinanceira.dao;

import br.ufrn.imd.carteirafinanceira.model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContaDAO {

    public Account get(int id) {
        Connection connection = ConnectionDAO.connect();
        try {
            PreparedStatement command = connection.prepareStatement("SELECT * FROM conta WHERE id = ?");
            command.setObject(1, id);

            ResultSet rSet = command.executeQuery();

            if (rSet.next()) return construirConta(rSet);
            else throw new RuntimeException("Conta inexistente.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDAO.disconnect();
        }
    }

    public boolean salvar(Account item) {
        String queryInsert = "insert into conta (nome, saldo, instituicao, cpf_usuario) values (?,?,?,?)";

        Connection connection = ConnectionDAO.connect();
        boolean result;

        try {
            PreparedStatement command = connection.prepareStatement(queryInsert);
            command.setString(1, item.getNome());
            command.setDouble(2, item.getSaldo());
            command.setString(3, item.getInstituicao());
            command.setString(4, item.getCpfUsuario());

            command.execute();

            result = true;
        }catch (SQLException sqlException){
            throw new RuntimeException(sqlException);
        }finally {
            ConnectionDAO.disconnect();
        }

        return result;
    }


    public List<Account> listarContasPorCpf(String cpfUsuario) {
        Connection connection = ConnectionDAO.connect();
        List<Account> list;

        try {
            PreparedStatement command = connection.prepareStatement("SELECT id, nome, instituicao, saldo FROM conta where cpf_usuario = ?");
            command.setString(1, cpfUsuario);

            ResultSet rSet = command.executeQuery();

            list = new ArrayList<>();
            while (rSet.next()) list.add(construirConta(rSet));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDAO.disconnect();
        }

        return list;
    }

    public void atualizarSaldo(Connection connection, Account account) {
        try {
            PreparedStatement command = connection.prepareStatement("UPDATE conta set saldo = ? WHERE id = ?");

            command.setDouble(1, account.getSaldo());
            command.setInt(2, account.getId());

            command.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Account construirConta(ResultSet rSet) throws SQLException {
        Account account = new Account();
        account.setId(rSet.getInt("id"));
        account.setSaldo(rSet.getDouble("saldo"));
        account.setNome(rSet.getString("nome"));
        account.setInstituicao(rSet.getString("instituicao"));
        return account;
    }
}

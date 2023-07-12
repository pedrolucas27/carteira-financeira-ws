package br.ufrn.imd.carteirafinanceira.dao;

import br.ufrn.imd.carteirafinanceira.model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {

    public Account get(Object columnValue) {
        String findQuery = String.format("SELECT * FROM conta WHERE %s = ?", getField(columnValue));

        Connection connection = ConnectionDAO.connect();
        try {
            PreparedStatement command = connection.prepareStatement(findQuery);
            command.setObject(1, columnValue);

            ResultSet rSet = command.executeQuery();

            if (rSet.next()) return buildAccount(rSet);
            else throw new RuntimeException("Conta inexistente.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDAO.disconnect();
        }
    }

    public boolean save(Account item) {
        String queryInsert = "insert into conta (nome, saldo, instituicao, cpf_usuario) values (?,?,?,?)";

        Connection connection = ConnectionDAO.connect();
        boolean result;

        try {
            PreparedStatement command = connection.prepareStatement(queryInsert);
            command.setString(1, item.getName());
            command.setDouble(2, item.getBalance());
            command.setString(3, item.getInstitution());
            command.setString(4, item.getUserCpf());

            command.execute();

            result = true;
        }catch (SQLException sqlException){
            throw new RuntimeException(sqlException);
        }finally {
            ConnectionDAO.disconnect();
        }

        return result;
    }


    public List<Account> listAll() {
        String selectQuery = "SELECT * FROM conta";

        Connection connection = ConnectionDAO.connect();
        List<Account> list;

        try {
            PreparedStatement command = connection.prepareStatement(selectQuery);
            ResultSet rSet = command.executeQuery();

            list = new ArrayList<>();
            while (rSet.next()) list.add(buildAccount(rSet));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDAO.disconnect();
        }

        return list;
    }

    public void updateBalance(Connection connection, Account account) {
        try {
            PreparedStatement command = connection.prepareStatement("UPDATE conta set saldo = ? WHERE id = ?");

            command.setDouble(1, account.getBalance());
            command.setInt(2, account.getId());

            command.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Account buildAccount(ResultSet rSet) throws SQLException {
        Account account = new Account(
                rSet.getString("nome"),
                rSet.getDouble("saldo"),
                rSet.getString("instituicao"),
                rSet.getString("cpf_usuario")
        );
        account.setId(rSet.getInt("id"));
        return account;
    }

    private String getField(Object columnValue) {
        String str = columnValue.getClass().getSimpleName();
        if (str.equals(Integer.class.getSimpleName())) {
            return  "id";
        } else if (str.equals(String.class.getSimpleName())) {
            return  "cpf_usuario";
        } else {
            throw new IllegalArgumentException("Tipo de dado inválido para filtro.");
        }
    }
}

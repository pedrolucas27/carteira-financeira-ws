package br.ufrn.imd.carteirafinanceira.dao;

import br.ufrn.imd.carteirafinanceira.model.Proof;
import br.ufrn.imd.carteirafinanceira.model.Spending;
import br.ufrn.imd.carteirafinanceira.model.Transaction;
import br.ufrn.imd.carteirafinanceira.service.TransactionService;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO implements TransactionService {
    public int generateIdTransaction(){
        String selectQuery = "SELECT MAX(id) as id_value FROM transacao";
        Connection connection = ConnectionDAO.connect();
        try {
            PreparedStatement command = connection.prepareStatement(selectQuery);
            ResultSet rSet = command.executeQuery();

            if (rSet.next()){
                return rSet.getInt("id_value")+1;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDAO.disconnect();
        }
        return 0;
    }
    private Date convertDate(LocalDate date){
        return java.sql.Date.valueOf(date);
    }

    public boolean saveSpeding(Spending item) {
        String insertQuery = "INSERT INTO despesa(status_pagamento, id_categoria, id_transacao) VALUES(?,?,?)";
        Connection connection = ConnectionDAO.connect();
        boolean result;
        try {
            PreparedStatement command = connection.prepareStatement(insertQuery);
            command.setInt(1, item.getStatus_pagamento());
            command.setInt(2, item.getId_categoria());
            command.setInt(3, item.getId());
            command.execute();
            result = true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDAO.disconnect();
        }
        return result;
    }

    public boolean saveProof(Proof item) {
        String insertQuery = "INSERT INTO receita(id_fonte, id_transacao) VALUES(?,?)";
        Connection connection = ConnectionDAO.connect();
        boolean result;
        try {
            PreparedStatement command = connection.prepareStatement(insertQuery);
            command.setInt(1, item.getId_fonte());
            command.setInt(2, item.getId());
            command.execute();
            result = true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDAO.disconnect();
        }
        return result;
    }

    @Override
    public int  saveTransaction(Transaction item) {
        String insertQuery = "INSERT INTO transacao(data, valor, id_conta, id) VALUES(?,?,?,?)";
        int id = generateIdTransaction();
        Connection connection = ConnectionDAO.connect();
        boolean result;
        try {
            PreparedStatement command = connection.prepareStatement(insertQuery);
            command.setDate(1, convertDate(item.getData()));
            command.setDouble(2, item.getValor());
            command.setInt(3, item.getId_conta());
            command.setInt(4, id);
            command.execute();
            result = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDAO.disconnect();
        }
        return id;
    }

    @Override
    public boolean processNewTransactionSpedin(Spending transaction) {
        int idTransaction = saveTransaction(transaction);
        transaction.setId(idTransaction);
        return saveSpeding(transaction);
    }

    @Override
    public boolean processNewTransactionProof(Proof transaction) {
        int idTransaction = saveTransaction(transaction);
        transaction.setId(idTransaction);
        return saveProof(transaction);
    }

    public List<transactionResponse> listAllByCpfUser(String cpf) {
        List<transactionResponse> list;
        Connection connection = ConnectionDAO.connect();
        String selectQuery = "SELECT * FROM transacao WHERE cpf_usuario =  ?";
        try {
            PreparedStatement command = connection.prepareStatement(selectQuery);
            command.setObject(1, cpf);
            ResultSet rSet = command.executeQuery();

            list = new ArrayList<>();
            while (rSet.next()){
                list.add(buildTransaction(rSet, ""));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDAO.disconnect();
        }

        return list;
    }

    private transactionResponse buildTransaction(ResultSet rSet, String type) throws SQLException {
        return new transactionResponse(
                type,
                rSet.getDouble("valor"),
                rSet.getDate("data"));
    }

    public record transactionResponse(String tipo, double valor, Date data_processamento){}
}

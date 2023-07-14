package br.ufrn.imd.carteirafinanceira.dao;

import br.ufrn.imd.carteirafinanceira.model.Account;
import br.ufrn.imd.carteirafinanceira.model.Transfer;
import br.ufrn.imd.carteirafinanceira.service.TransferService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransferDAO implements TransferService {

    private final ContaDAO contaDAO = new ContaDAO();

    @Override
    public void save(Transfer transfer) {
        Connection connection = ConnectionDAO.connect();
        try {
            PreparedStatement command = connection.prepareStatement("INSERT INTO transferencia (id_conta_origem, id_conta_destino, valor) VALUES (?, ?, ?)");
            command.setInt(1, transfer.getIdContaOrigem());
            command.setInt(2, transfer.getIdContaDestino());
            command.setDouble(3, transfer.getValor());
            command.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDAO.disconnect();
        }
    }


    @Override
    public void performTransfer(Transfer transfer) throws RuntimeException {
        validateParameters(transfer.getIdContaOrigem(), transfer.getIdContaDestino(), transfer.getValor());

        Account originAccount = contaDAO.get(transfer.getIdContaOrigem());
        Account destinyAccount = contaDAO.get(transfer.getIdContaDestino());

        if (originAccount.getSaldo() < transfer.getValor()) {
            throw new RuntimeException("Saldo insuficiente na conta de origem.");
        }

        boolean result = process(originAccount, destinyAccount, transfer.getValor());

        if(result) this.save(transfer);
        else throw new RuntimeException("Não foi possível realizar esta operação.");
    }


    private boolean process(Account originAccount, Account destinyAccount, double amount) {
        Connection connection = ConnectionDAO.connect();
        boolean result = false;

        try {
            connection.setAutoCommit(false);

            originAccount.setSaldo(originAccount.getSaldo() - amount);
            destinyAccount.setSaldo(destinyAccount.getSaldo() + amount);

            contaDAO.atualizarSaldo(connection, originAccount);
            contaDAO.atualizarSaldo(connection, destinyAccount);

            connection.commit();
            result = true;
        }catch (SQLException e){
            rollback(connection);
        } finally {
            ConnectionDAO.disconnect();
        }
        return result;
    }

    private void rollback(Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao realizar rollback da transação: " + ex.getMessage(), ex);
        }
    }

    private void validateParameters(int originAccountId, int destinyAccountId, double amount) throws IllegalArgumentException {
        if(amount <= 0) {
            throw new IllegalArgumentException("O valor informado para transferência é inválido.");
        }
        if(originAccountId == destinyAccountId) {
            throw new IllegalArgumentException("A conta de destino informada é inválida.");
        }
    }
}

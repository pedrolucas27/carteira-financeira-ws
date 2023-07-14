package br.ufrn.imd.carteirafinanceira.dao;

import br.ufrn.imd.carteirafinanceira.model.Conta;
import br.ufrn.imd.carteirafinanceira.model.Transferencia;
import br.ufrn.imd.carteirafinanceira.service.TransferService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransferenciaDAO implements TransferService {

    private final ContaDAO contaDAO = new ContaDAO();

    @Override
    public void save(Transferencia transferencia) {
        Connection connection = ConnectionDAO.connect();
        try {
            PreparedStatement command = connection.prepareStatement("INSERT INTO transferencia (id_conta_origem, id_conta_destino, valor) VALUES (?, ?, ?)");
            command.setInt(1, transferencia.getIdContaOrigem());
            command.setInt(2, transferencia.getIdContaDestino());
            command.setDouble(3, transferencia.getValor());
            command.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDAO.disconnect();
        }
    }


    @Override
    public void performTransfer(Transferencia transferencia) throws RuntimeException {
        validateParameters(transferencia.getIdContaOrigem(), transferencia.getIdContaDestino(), transferencia.getValor());

        Conta originConta = contaDAO.get(transferencia.getIdContaOrigem());
        Conta destinyConta = contaDAO.get(transferencia.getIdContaDestino());

        if (originConta.getSaldo() < transferencia.getValor()) {
            throw new RuntimeException("Saldo insuficiente na conta de origem.");
        }

        boolean result = process(originConta, destinyConta, transferencia.getValor());

        if(result) this.save(transferencia);
        else throw new RuntimeException("Não foi possível realizar esta operação.");
    }


    private boolean process(Conta originConta, Conta destinyConta, double amount) {
        Connection connection = ConnectionDAO.connect();
        boolean result = false;

        try {
            connection.setAutoCommit(false);

            originConta.setSaldo(originConta.getSaldo() - amount);
            destinyConta.setSaldo(destinyConta.getSaldo() + amount);

            contaDAO.atualizarSaldo(connection, originConta);
            contaDAO.atualizarSaldo(connection, destinyConta);

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

package br.ufrn.imd.carteirafinanceira.dao;

import br.ufrn.imd.carteirafinanceira.model.FinancialGoal;
import br.ufrn.imd.carteirafinanceira.model.User;
import br.ufrn.imd.carteirafinanceira.service.CrudService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class FinancialGoalDAO implements CrudService<FinancialGoal> {

    UserDAO userDAO = new UserDAO();
    @Override
    public FinancialGoal get(Object columnValue) {
        return null;
    }

    @Override
    public boolean save(FinancialGoal item) {

        User user = userDAO.get(item.getCpf_usuario());
        if(Objects.nonNull(user)) return false;

        String insertQuery = "INSERT INTO " +
                "meta_financeira(nome, valor_meta , valor_atual, cpf_usuario) " +
                "VALUES(?,?,?,?)";

        Connection connection = ConnectionDAO.connect();
        boolean result;

        try {
            PreparedStatement command = connection.prepareStatement(insertQuery);

            command.setString(1, item.getNome());
            command.setDouble(2, item.getValor_meta());
            command.setDouble(3, item.getValor_atual());
            command.setString(4, item.getCpf_usuario());

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
    public boolean delete(FinancialGoal item) {
        return false;
    }

    @Override
    public List<FinancialGoal> listAll() {
        return null;
    }

    @Override
    public boolean update(FinancialGoal item) {
        return false;
    }
}

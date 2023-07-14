package br.ufrn.imd.carteirafinanceira.dao;

import br.ufrn.imd.carteirafinanceira.model.FinancialGoal;
import br.ufrn.imd.carteirafinanceira.model.User;
import br.ufrn.imd.carteirafinanceira.service.CrudService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        if(Objects.isNull(user)) return false;

        String insertQuery = "INSERT INTO " +
                "meta_financeira(nome, valor_meta , valor_atual,descricao, cpf_usuario) " +
                "VALUES(?,?,?,?,?)";

        Connection connection = ConnectionDAO.connect();
        boolean result;

        try {
            PreparedStatement command = connection.prepareStatement(insertQuery);

            command.setString(1, item.getNome());
            command.setDouble(2, item.getValor_meta());
            command.setDouble(3, item.getValor_atual());
            command.setString(4, item.getDescricao());
            command.setString(5, item.getCpf_usuario());

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

    public List<GoalResponse> listAllByCpfUser(String cpf){
        List<GoalResponse> list;
        Connection connection = ConnectionDAO.connect();
        String selectQuery = "SELECT * FROM meta_financeira WHERE cpf_usuario =  ?";
        try {
            PreparedStatement command = connection.prepareStatement(selectQuery);
            command.setObject(1, cpf);
            ResultSet rSet = command.executeQuery();

            list = new ArrayList<>();
            while (rSet.next()){
                list.add(buildGoal(rSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDAO.disconnect();
        }

        return list;
    }

    @Override
    public boolean update(FinancialGoal item) {
        return false;
    }

    private GoalResponse buildGoal(ResultSet rSet) throws SQLException {
        int valor = rSet.getInt("valor_meta") - rSet.getInt("valor_atual");
        return new GoalResponse (
                rSet.getString("nome"),
                valor
        );
    }

    public record GoalResponse(String nome, int valor_pendente){}
}

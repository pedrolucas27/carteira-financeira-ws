package br.ufrn.imd.carteirafinanceira.dao;

import br.ufrn.imd.carteirafinanceira.model.BasicFonte;
import br.ufrn.imd.carteirafinanceira.model.Loan;
import br.ufrn.imd.carteirafinanceira.service.CrudService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class LoanDAO implements CrudService<Loan> {


    public BasicFonte getFonteByName(String name){
        String selectQuery = "SELECT * FROM fonte_emprestimo WHERE nome = ?";

        Connection connection = ConnectionDAO.connect();
        try {
            PreparedStatement command = connection.prepareStatement(selectQuery);
            command.setString(1, name);

            ResultSet rSet = command.executeQuery();

            if (rSet.next()){
                return BasicFonte.buldFonte(rSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDAO.disconnect();
        }

        return null;
    }

    public boolean registerFonte(BasicFonte basicFonte){
        if(Objects.nonNull(getFonteByName(basicFonte.getNome()))) return true;

        String insertQuery = "INSERT INTO fonte_emprestimo(nome) VALUES(?)";
        Connection connection = ConnectionDAO.connect();
        boolean result = false;

        try {
            PreparedStatement command = connection.prepareStatement(insertQuery);

            command.setString(1, basicFonte.getNome());
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
    public Loan get(Object columnValue) {
        return null;
    }

    @Override
    public boolean save(Loan item) {
        return false;
    }

    @Override
    public boolean delete(Loan item) {
        return false;
    }

    @Override
    public List<Loan> listAll() {
        return null;
    }

    @Override
    public boolean update(Loan item) {
        return false;
    }
}

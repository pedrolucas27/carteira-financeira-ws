package br.ufrn.imd.carteirafinanceira.dao;
import br.ufrn.imd.carteirafinanceira.model.CreditCard;
import br.ufrn.imd.carteirafinanceira.model.Usuario;
import br.ufrn.imd.carteirafinanceira.service.CrudService;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CreditCardDAO implements CrudService<CreditCard> {
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();


    private Date convertDate(LocalDate date){
        return java.sql.Date.valueOf(date);
    }
    @Override
    public CreditCard get(Object columnValue) {
        String findQuery = "SELECT * FROM cartao_de_credito WHERE id = ?";

        Connection connection = ConnectionDAO.connect();
        try {
            PreparedStatement command = connection.prepareStatement(findQuery);
            command.setObject(1, columnValue);

            ResultSet rSet = command.executeQuery();

            if (rSet.next()){
                return buildCreditCard(rSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDAO.disconnect();
        }

        return null;
    }

    @Override
    public boolean save(CreditCard item) {

         Usuario usuario = usuarioDAO.get(item.getCpf_usuario());

         if(Objects.isNull(usuario)){
                return false;
         }

        String insertQuery = "INSERT INTO cartao_de_credito(id, limite, data_fechamento, cpf_usuario, id_conta) VALUES(?,?,?,?,?)";

        Connection connection = ConnectionDAO.connect();
        boolean result=false;

        try {
                PreparedStatement command = connection.prepareStatement(insertQuery);

                command.setInt(1, item.getId());
                command.setBigDecimal(2, BigDecimal.valueOf(item.getLimite()));
                command.setDate(3, convertDate(item.getData_fechamento()));
                command.setString(4, item.getCpf_usuario());
                command.setInt(5, 1);

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
    public boolean delete(CreditCard item) {

        String deleteQuery = "DELETE FROM carteira_credito WHERE id = ?";

        Connection connection = ConnectionDAO.connect();
        boolean result;

        try {
            PreparedStatement command = connection.prepareStatement(deleteQuery);

            command.setInt(1, item.getId());
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
    public List<CreditCard> listAll() {
        String selectQuery = "SELECT * FROM cartao_de_credito";

        Connection connection = ConnectionDAO.connect();
        List<CreditCard> list;

        try {
            PreparedStatement command = connection.prepareStatement(selectQuery);
            ResultSet rSet = command.executeQuery();

            list = new ArrayList<>();
            while (rSet.next()){
                list.add(buildCreditCard(rSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDAO.disconnect();
        }

        return list;
    }

    private CreditCard buildCreditCard(ResultSet rSet) throws SQLException {
        return new CreditCard(
                rSet.getInt("id"),
                rSet.getBigDecimal("limite").floatValue(),
                rSet.getDate("data_fechamento").toLocalDate(),
                rSet.getString("cpf_usuario"),
                rSet.getInt("id_conta")
        );
    }

    @Override
    public boolean update(CreditCard item) {
        String updateQuery = "UPDATE cartao_credito set id = ?, limite = ?, data_fechamento = ?, cpf_usuario = ? WHERE id_conta = ?";

        Connection connection = ConnectionDAO.connect();
        boolean result;
        try {
            PreparedStatement command = connection.prepareStatement(updateQuery);

            command.setInt(1, item.getId());
            command.setBigDecimal(2, BigDecimal.valueOf(item.getLimite()));
            command.setDate(3,  convertDate(item.getData_fechamento()));
            command.setString(4, item.getCpf_usuario());
            command.setInt(5, item.getId_conta());

            command.execute();

            result = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDAO.disconnect();
        }

        return result;
    }
}

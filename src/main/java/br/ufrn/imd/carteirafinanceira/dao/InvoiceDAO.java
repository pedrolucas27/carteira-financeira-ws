package br.ufrn.imd.carteirafinanceira.dao;

import br.ufrn.imd.carteirafinanceira.model.CreditCard;
import br.ufrn.imd.carteirafinanceira.model.Invoice;
import br.ufrn.imd.carteirafinanceira.service.CrudService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InvoiceDAO  implements CrudService<Invoice> {

    CreditCardDAO creditCardDAO = new CreditCardDAO();
    @Override
    public Invoice get(Object columnValue) {
        Invoice invoice = (Invoice) columnValue;
        String findQuery = "SELECT * FROM fatura WHERE mes = ? and ano = ? and id_cartao = ?";

        Connection connection = ConnectionDAO.connect();
        try {
            PreparedStatement command = connection.prepareStatement(findQuery);
            command.setInt(1, invoice.getMes());
            command.setInt(2, invoice.getAno());
            command.setInt(3, invoice.getId_cartao());

            ResultSet rSet = command.executeQuery();

            if (rSet.next()){
                return buildInvoice(rSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDAO.disconnect();
        }

        return null;
    }

    @Override
    public boolean save(Invoice item) {
        CreditCard card = creditCardDAO.get(item.getId_cartao());
        if(Objects.isNull(card)) return false;

        String insertQuery = "INSERT INTO fatura(mes, ano, id_cartao, id_transacao_despesa) VALUES(?,?,?,?)";

        Connection connection = ConnectionDAO.connect();
        boolean result=false;

        try {
            PreparedStatement command = connection.prepareStatement(insertQuery);

            command.setInt(1, item.getMes());
            command.setInt(2, item.getAno());
            command.setInt(3, item.getId_cartao());
            command.setInt(4, item.getId_transacao_despesa());

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
    public boolean delete(Invoice item) {
        String deleteQuery = "DELETE FROM fatura WHERE mes = ? and ano = ? and id_cartao = ?";

        Connection connection = ConnectionDAO.connect();
        boolean result;

        try {
            PreparedStatement command = connection.prepareStatement(deleteQuery);

            command.setInt(1, item.getMes());
            command.setInt(2, item.getAno());
            command.setInt(3, item.getId_cartao());
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
    public List<Invoice> listAll() {
        String selectQuery = "SELECT * FROM fatura";

        Connection connection = ConnectionDAO.connect();
        List<Invoice> list;

        try {
            PreparedStatement command = connection.prepareStatement(selectQuery);
            ResultSet rSet = command.executeQuery();

            list = new ArrayList<>();
            while (rSet.next()){
                list.add(buildInvoice(rSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDAO.disconnect();
        }

        return list;
    }

    @Override
    public boolean update(Invoice item) {
        //atualiza somente a despesa mensal
        String insertQuery = "UPDATE fatura SET id_transacao_despesa = ?, " +
                "WHERE  mes = ? and ano = ? and id_cartao = ?";

        Connection connection = ConnectionDAO.connect();
        boolean result=false;

        try {
            PreparedStatement command = connection.prepareStatement(insertQuery);

            command.setInt(1, item.getId_transacao_despesa());
            command.setInt(2, item.getMes());
            command.setInt(3, item.getAno());
            command.setInt(4, item.getId_cartao());


            command.execute();

            result = true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDAO.disconnect();
        }

        return result;
    }

    private Invoice buildInvoice(ResultSet rSet) throws SQLException {
        return new Invoice(
                rSet.getInt("mes"),
                rSet.getInt("ano"),
                rSet.getInt("id_cartao"),
                rSet.getInt("id_transacao_despesa")
        );
    }
}

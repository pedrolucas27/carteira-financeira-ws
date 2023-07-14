package br.ufrn.imd.carteirafinanceira.dao;

import br.ufrn.imd.carteirafinanceira.model.Usuario;
import br.ufrn.imd.carteirafinanceira.service.CrudService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO implements CrudService<Usuario> {

    @Override
    public Usuario get(Object columnValue) {
        Connection connection = ConnectionDAO.connect();

        try {
            PreparedStatement command = connection.prepareStatement("SELECT * FROM usuario WHERE cpf = ?");
            command.setObject(1, columnValue);

            ResultSet rSet = command.executeQuery();

            if (rSet.next()){
                return buildUser(rSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDAO.disconnect();
        }

        return null;
    }

    @Override
    public boolean save(Usuario item) {
        String insertQuery = "INSERT INTO usuario(cpf, nome, login, senha, email) VALUES(?,?,?,?,?)";

        Connection connection = ConnectionDAO.connect();
        boolean result;

        try {
            PreparedStatement command = connection.prepareStatement(insertQuery);

            command.setString(1, item.getCpf());
            command.setString(2, item.getNome());
            command.setString(3, item.getLogin());
            command.setString(4, item.getSenha());
            command.setString(5, item.getEmail());

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
    public boolean delete(Usuario item) {
        Connection connection = ConnectionDAO.connect();
        boolean result;

        try {
            PreparedStatement command = connection.prepareStatement("DELETE FROM usuario WHERE cpf = ?");

            command.setString(1, item.getCpf());
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
    public List<Usuario> listAll() {
        Connection connection = ConnectionDAO.connect();
        List<Usuario> list;

        try {
            PreparedStatement command = connection.prepareStatement("SELECT * FROM usuario");
            ResultSet rSet = command.executeQuery();

            list = new ArrayList<>();
            while (rSet.next()){
                list.add(buildUser(rSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDAO.disconnect();
        }

        return list;
    }

    @Override
    public boolean update(Usuario item) {
        String updateQuery = "UPDATE usuario set cpf = ?, nome = ?, login = ?, email = ? WHERE cpf = ?";

        Connection connection = ConnectionDAO.connect();
        boolean result;
        try {
            PreparedStatement command = connection.prepareStatement(updateQuery);

            command.setString(1, item.getCpf());
            command.setString(2, item.getNome());
            command.setString(3, item.getLogin());
            command.setString(4, item.getEmail());

            command.execute();

            result = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDAO.disconnect();
        }

        return result;
    }

    private Usuario buildUser(ResultSet rSet) throws SQLException {
        Usuario usuario = new Usuario(
                rSet.getString("cpf"),
                rSet.getString("nome"),
                rSet.getString("login"),
                null,
                rSet.getString("email")
                );
        return usuario;
    }
}

package br.ufrn.imd.carteirafinanceira.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class ConnectionDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/carteira_financeira";
    private static final String USER = "root";
    private static final String PASSWORD = "pedro@br";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    private static Connection connection;

    public static Connection connect(){
        if(Objects.isNull(connection)){
            try {
                Class.forName(DRIVER);
                connection =  DriverManager.getConnection(URL, USER, PASSWORD);
                return connection;
            } catch (SQLException | ClassNotFoundException e) {
                System.err.println(e.getMessage());
            }
        }
        return connection;
    }

    public static void disconnect(){
        if(Objects.nonNull(connection)){
            try{
                 connection.close();
                 connection = null;
            } catch(SQLException ex){
                System.err.println(ex.getMessage());
            }
        }
    }

}

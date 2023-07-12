package br.ufrn.imd.carteirafinanceira.model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
public class BasicFonte implements Serializable {
    int id;
    String nome;

    public BasicFonte(String nome) {
        this.nome = nome;
    }

    public static BasicFonte buldFonte(ResultSet rSet) throws SQLException {
        return new BasicFonte(rSet.getString("nome"));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}

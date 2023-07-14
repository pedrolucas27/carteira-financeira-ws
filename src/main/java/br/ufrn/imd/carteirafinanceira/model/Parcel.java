package br.ufrn.imd.carteirafinanceira.model;

import java.io.Serializable;

public class Parcel implements Serializable {

    int mes;
    int ano;
    int id_emprestimo;
    int id_transacao_despesa;

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getId_emprestimo() {
        return id_emprestimo;
    }

    public void setId_emprestimo(int id_emprestimo) {
        this.id_emprestimo = id_emprestimo;
    }

    public int getId_transacao_despesa() {
        return id_transacao_despesa;
    }

    public void setId_transacao_despesa(int id_transacao_despesa) {
        this.id_transacao_despesa = id_transacao_despesa;
    }
}

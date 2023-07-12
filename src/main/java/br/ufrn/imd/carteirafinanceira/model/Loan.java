package br.ufrn.imd.carteirafinanceira.model;

import java.io.Serializable;

public class Loan implements Serializable {
    int id;
    int numero_parcelas;
    int id_transacao;
    int id_fonte;
    double juros;

    public Loan(int numero_parcelas, int id_transacao, int id_fonte, double juros) {
        this.numero_parcelas = numero_parcelas;
        this.id_transacao = id_transacao;
        this.id_fonte = id_fonte;
        this.juros = juros;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero_parcelas() {
        return numero_parcelas;
    }

    public void setNumero_parcelas(int numero_parcelas) {
        this.numero_parcelas = numero_parcelas;
    }

    public int getId_transacao() {
        return id_transacao;
    }

    public void setId_transacao(int id_transacao) {
        this.id_transacao = id_transacao;
    }

    public int getId_fonte() {
        return id_fonte;
    }

    public void setId_fonte(int id_fonte) {
        this.id_fonte = id_fonte;
    }

    public double getJuros() {
        return juros;
    }

    public void setJuros(double juros) {
        this.juros = juros;
    }
}

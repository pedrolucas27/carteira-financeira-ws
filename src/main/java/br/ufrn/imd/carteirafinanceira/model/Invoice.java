package br.ufrn.imd.carteirafinanceira.model;

import java.io.Serializable;

public class Invoice implements Serializable {
    private int mes;
    private int ano;
    private int id_cartao;
    private int id_transacao_despesa;

    public Invoice(int mes, int ano, int id_cartao, int id_transacao) {
        this.mes = mes;
        this.ano = ano;
        this.id_cartao = id_cartao;
        this.id_transacao_despesa = id_transacao;
    }

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

    public int getId_cartao() {
        return id_cartao;
    }

    public void setId_cartao(int id_cartao) {
        this.id_cartao = id_cartao;
    }

    public int getId_transacao_despesa() {
        return id_transacao_despesa;
    }

    public void setId_transacao_despesa(int id_transacao_despesa) {
        this.id_transacao_despesa = id_transacao_despesa;
    }
}

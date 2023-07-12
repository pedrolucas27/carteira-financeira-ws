package br.ufrn.imd.carteirafinanceira.model;

public class Proof extends Transaction {
    int id_fonte;

    public int getId_fonte() {
        return id_fonte;
    }

    public void setId_fonte(int id_fonte) {
        this.id_fonte = id_fonte;
    }
}

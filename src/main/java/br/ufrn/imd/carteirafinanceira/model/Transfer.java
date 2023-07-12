package br.ufrn.imd.carteirafinanceira.model;

public class Transfer {

    private int id;
    private int originAccountId;
    private int destinyAccountId;
    private double amount;

    public Transfer(int originAccountId, int destinyAccountId, double amount) {
        this.originAccountId = originAccountId;
        this.destinyAccountId = destinyAccountId;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOriginAccountId() {
        return originAccountId;
    }

    public void setOriginAccountId(int originAccountId) {
        this.originAccountId = originAccountId;
    }

    public int getDestinyAccountId() {
        return destinyAccountId;
    }

    public void setDestinyAccountId(int destinyAccountId) {
        this.destinyAccountId = destinyAccountId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}

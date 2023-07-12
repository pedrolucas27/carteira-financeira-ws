package br.ufrn.imd.carteirafinanceira.model;

import java.io.Serializable;

public class Account implements Serializable {

    private int id;
    private String name;
    private double balance;
    private String institution;
    private String userCpf;

    public Account(String name, double balance, String institution, String userCpf) {
        this.name = name;
        this.balance = balance;
        this.institution = institution;
        this.userCpf = userCpf;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getUserCpf() {
        return userCpf;
    }

    public void setUserCpf(String userCpf) {
        this.userCpf = userCpf;
    }
}

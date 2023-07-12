package br.ufrn.imd.carteirafinanceira.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public class CreditCard implements Serializable {
    private int id;
    private float limite;
    private LocalDate data_fechamento;
    private String cpf_usuario;
    private int id_conta;

    public CreditCard(int id, float limite, LocalDate data_fechamento, String cpf_usuario, int id_conta) {
        //gerar ids aleatorios
        this.id = id;
        this.limite = limite;
        this.data_fechamento = data_fechamento;
        this.cpf_usuario = cpf_usuario;
        this.id_conta = id_conta;
        System.out.println(id);
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                ", limite=" + limite +
                ", data_fechamento=" + data_fechamento +
                ", cpf_usuario='" + cpf_usuario + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getLimite() {
        return limite;
    }

    public void setLimite(float limite) {
        this.limite = limite;
    }

    public LocalDate getData_fechamento() {
        return data_fechamento;
    }

    public void setData_fechamento(LocalDate data_fechamento) {
        this.data_fechamento = data_fechamento;
    }

    public String getCpf_usuario() {
        return cpf_usuario;
    }

    public void setCpf_usuario(String cpf_usuario) {
        this.cpf_usuario = cpf_usuario;
    }

    public int getId_conta() {
        return id_conta;
    }

    public void setId_conta(int id_conta) {
        this.id_conta = id_conta;
    }
}

package br.ufrn.imd.carteirafinanceira.model;

import java.io.Serializable;

public class FinancialGoal implements Serializable {
    int id;
    String nome;
    double valor_meta;
    double valor_atual;
    String descricao;
    String cpf_usuario;

    public FinancialGoal(
            String nome,
            double valor_meta,
            double valor_atual,
            String descricao,
            String cpf_usuario
    ) {
        this.nome = nome;
        this.valor_meta = valor_meta;
        this.valor_atual = valor_atual;
        this.descricao = descricao;
        this.cpf_usuario = cpf_usuario;
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

    public double getValor_meta() {
        return valor_meta;
    }

    public void setValor_meta(double valor_meta) {
        this.valor_meta = valor_meta;
    }

    public double getValor_atual() {
        return valor_atual;
    }

    public void setValor_atual(double valor_atual) {
        this.valor_atual = valor_atual;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCpf_usuario() {
        return cpf_usuario;
    }

    public void setCpf_usuario(String cpf_usuario) {
        this.cpf_usuario = cpf_usuario;
    }
}

package br.ufrn.imd.carteirafinanceira.model;

import java.io.Serializable;

enum TypeFonte{
    RECEITA,
    EMPRESTIMO
}
public class BasicFonte implements Serializable {
    int id;
    String nome;
    TypeFonte typeFonte;
}

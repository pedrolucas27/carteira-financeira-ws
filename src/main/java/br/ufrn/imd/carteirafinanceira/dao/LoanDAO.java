package br.ufrn.imd.carteirafinanceira.dao;

import br.ufrn.imd.carteirafinanceira.model.BasicFonte;
import br.ufrn.imd.carteirafinanceira.model.Loan;
import br.ufrn.imd.carteirafinanceira.service.CrudService;

import java.util.List;

public class LoanDAO implements CrudService<Loan> {

    public void registerFonte(BasicFonte basicFonte){
        String insertQuery = "INSERT INTO fonte_emprestimo(limite) VALUES(?)";
    }


    @Override
    public Loan get(Object columnValue) {
        return null;
    }

    @Override
    public boolean save(Loan item) {
        return false;
    }

    @Override
    public boolean delete(Loan item) {
        return false;
    }

    @Override
    public List<Loan> listAll() {
        return null;
    }

    @Override
    public boolean update(Loan item) {
        return false;
    }
}

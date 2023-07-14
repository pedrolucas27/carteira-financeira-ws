package br.ufrn.imd.carteirafinanceira.service;

import br.ufrn.imd.carteirafinanceira.model.Proof;
import br.ufrn.imd.carteirafinanceira.model.Spending;
import br.ufrn.imd.carteirafinanceira.model.Transaction;

public interface TransactionService {

    public boolean processNewTransactionSpedin(Spending transaction);

    boolean processNewTransactionProof(Proof transaction);

    public int  saveTransaction(Transaction item);
}

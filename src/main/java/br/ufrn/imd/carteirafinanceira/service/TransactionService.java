package br.ufrn.imd.carteirafinanceira.service;

import br.ufrn.imd.carteirafinanceira.model.Proof;
import br.ufrn.imd.carteirafinanceira.model.Spending;
import br.ufrn.imd.carteirafinanceira.model.Transaction;

public interface TransactionService {

    boolean processNewTransactionSpedin(Spending transaction);

    boolean processNewTransactionProof(Proof transaction);

    int  saveTransaction(Transaction item);
}

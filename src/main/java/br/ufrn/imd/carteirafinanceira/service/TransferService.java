package br.ufrn.imd.carteirafinanceira.service;

import br.ufrn.imd.carteirafinanceira.model.Transfer;

import java.sql.SQLException;

public interface TransferService {

    void save(Transfer transfer);

    void performTransfer(Transfer transfer) throws SQLException, IllegalArgumentException;
}

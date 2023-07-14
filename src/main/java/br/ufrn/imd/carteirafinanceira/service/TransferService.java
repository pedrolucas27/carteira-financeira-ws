package br.ufrn.imd.carteirafinanceira.service;

import br.ufrn.imd.carteirafinanceira.model.Transferencia;

import java.sql.SQLException;

public interface TransferService {

    void save(Transferencia transferencia);

    void performTransfer(Transferencia transferencia) throws SQLException, IllegalArgumentException;
}

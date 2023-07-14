package br.ufrn.imd.carteirafinanceira.controller;

import br.ufrn.imd.carteirafinanceira.dao.TransferenciaDAO;
import br.ufrn.imd.carteirafinanceira.model.Transferencia;
import br.ufrn.imd.carteirafinanceira.service.TransferService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/transfer")
public class TransferenciaController {

    private final TransferService transferService = new TransferenciaDAO();

    @PostMapping
    public ResponseEntity<?> transfer(@RequestBody Transferencia transferencia) {
        try {
            transferService.performTransfer(transferencia);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

package br.ufrn.imd.carteirafinanceira.controller;

import br.ufrn.imd.carteirafinanceira.dao.TransferDAO;
import br.ufrn.imd.carteirafinanceira.model.Transfer;
import br.ufrn.imd.carteirafinanceira.service.TransferService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/transfer")
public class TransferController {

    private final TransferService transferService = new TransferDAO();

    @PostMapping
    public ResponseEntity<?> transfer(@RequestBody Transfer transfer) {
        try {
            transferService.performTransfer(transfer);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

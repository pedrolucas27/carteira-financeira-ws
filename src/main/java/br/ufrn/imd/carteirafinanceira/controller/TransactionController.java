package br.ufrn.imd.carteirafinanceira.controller;

import br.ufrn.imd.carteirafinanceira.dao.FinancialGoalDAO;
import br.ufrn.imd.carteirafinanceira.dao.TransactionDAO;
import br.ufrn.imd.carteirafinanceira.model.Proof;
import br.ufrn.imd.carteirafinanceira.model.Spending;
import br.ufrn.imd.carteirafinanceira.model.Transaction;
import br.ufrn.imd.carteirafinanceira.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/register")
public class TransactionController {

    TransactionDAO service = new TransactionDAO();
    //register Speding

    @PostMapping(value = "speding", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> registerSpeding(@RequestBody Spending spending){
        ResponseEntity<String> response;
        boolean result = service.processNewTransactionSpedin(spending);
        if(result) return new ResponseEntity<>("despesa registrada com sucesso!", HttpStatus.OK);
       return new ResponseEntity<String>("erro ao efetuar transação!", HttpStatus.BAD_REQUEST);
    }

    // register Proof
    @PostMapping(value= "proof", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> registerProof(@RequestBody Proof proof){
        boolean result = service.processNewTransactionProof(proof);
        if(result) return new ResponseEntity<>("receita registrada com sucesso!",HttpStatus.OK);
        return new ResponseEntity<String>("erro ao efetuar transação!", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("{cpf}")
    public ResponseEntity<List<FinancialGoalDAO.GoalResponse>> listAllByCpfUser(@PathVariable String cpf){
        List<FinancialGoalDAO.GoalResponse> result = service.listAllByCpfUser(cpf);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}

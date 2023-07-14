package br.ufrn.imd.carteirafinanceira.controller;

import br.ufrn.imd.carteirafinanceira.dao.LoanDAO;
import br.ufrn.imd.carteirafinanceira.model.BasicSource;
import br.ufrn.imd.carteirafinanceira.model.Loan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/emprestimo")
public class LoanController {

    private final LoanDAO service = new LoanDAO();

    @PostMapping(value = "fonte", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Boolean> registerFonte(@RequestBody BasicSource fonte){
        boolean result = false;
        try {
            result = service.registerFonte(fonte);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(result, result ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Boolean> save(@RequestBody Loan loan){
        boolean result = false;
        try {
            result = service.save(loan);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(result, result ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }



    @PutMapping
    public  ResponseEntity<Boolean> updateCard(@RequestBody Loan loan){
        boolean result = service.update(loan);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Loan>> listAll(){
        List<Loan> result = service.listAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Loan> getById(@PathVariable int id){
        Loan result = service.get(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Boolean> delete(@PathVariable int id){
        Loan loan = service.get(id);
        boolean result = service.delete(loan);
        return new ResponseEntity<>(result, result ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
}

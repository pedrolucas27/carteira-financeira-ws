package br.ufrn.imd.carteirafinanceira.controller;

import br.ufrn.imd.carteirafinanceira.dao.AccountDAO;
import br.ufrn.imd.carteirafinanceira.model.Account;
import br.ufrn.imd.carteirafinanceira.service.CrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/account")
public class AccountController {

    private final AccountDAO service = new AccountDAO();

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Boolean> save(@RequestBody Account account){
        boolean result = service.save(account);
        return new ResponseEntity<>(result, result ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    public ResponseEntity<List<Account>> listAll(){
        List<Account> result = service.listAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Account> findById(@PathVariable Integer id){
        Account account = service.get(id);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @GetMapping(value = "/{cpf}")
    public ResponseEntity<Account> findByCpf(@PathVariable String cpf){
        Account account = service.get(cpf);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

}

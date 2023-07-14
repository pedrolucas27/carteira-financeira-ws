package br.ufrn.imd.carteirafinanceira.controller;

import br.ufrn.imd.carteirafinanceira.dao.CreditCardDAO;
import br.ufrn.imd.carteirafinanceira.model.CreditCard;
import br.ufrn.imd.carteirafinanceira.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/cartao-credito")

public class CreditCardController {
    private final CreditCardDAO service = new CreditCardDAO();

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Boolean> save(@RequestBody CreditCard card){
        boolean result = false;
        try {
            result = service.save(card);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(result, result ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @PutMapping
    public  ResponseEntity<Boolean> updateCard(@RequestBody CreditCard creditCard){
        boolean result = service.update(creditCard);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CreditCard>> listAll(){
        List<CreditCard> result = service.listAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<CreditCard> getById(@PathVariable int id){
        CreditCard result = service.get(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Boolean> delete(@PathVariable int id){
        CreditCard creditCard = service.get(id);
        boolean result = service.delete(creditCard);
        return new ResponseEntity<>(result, result ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
}

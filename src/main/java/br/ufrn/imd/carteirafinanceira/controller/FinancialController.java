package br.ufrn.imd.carteirafinanceira.controller;


import br.ufrn.imd.carteirafinanceira.dao.FinancialGoalDAO;
import br.ufrn.imd.carteirafinanceira.model.FinancialGoal;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/meta-financeira")
public class FinancialController {
    private final FinancialGoalDAO service = new FinancialGoalDAO();
    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Boolean> save(@RequestBody FinancialGoal goal){
        boolean result = false;
        try {
            result = service.save(goal);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(result, result ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
}

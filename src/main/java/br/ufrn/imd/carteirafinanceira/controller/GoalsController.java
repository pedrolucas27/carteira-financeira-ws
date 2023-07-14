package br.ufrn.imd.carteirafinanceira.controller;


import br.ufrn.imd.carteirafinanceira.dao.FinancialGoalDAO;
import br.ufrn.imd.carteirafinanceira.model.FinancialGoal;
import br.ufrn.imd.carteirafinanceira.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/meta-financeira")
public class GoalsController {
    private final FinancialGoalDAO service = new FinancialGoalDAO();
    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Boolean> saveNewGoal(@RequestBody FinancialGoal goal){
        boolean result = false;
        try {
            result = service.save(goal);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(result, result ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @GetMapping("{cpf}")
    public ResponseEntity<List<FinancialGoalDAO.GoalResponse>> listAllByCpfUser(@PathVariable String cpf){
        List<FinancialGoalDAO.GoalResponse> result = service.listAllByCpfUser(cpf);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}

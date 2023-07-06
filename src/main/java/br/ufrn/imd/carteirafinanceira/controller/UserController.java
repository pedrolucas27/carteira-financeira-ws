package br.ufrn.imd.carteirafinanceira.controller;

import br.ufrn.imd.carteirafinanceira.dao.UserDAO;
import br.ufrn.imd.carteirafinanceira.model.User;
import br.ufrn.imd.carteirafinanceira.service.CrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    private final CrudService<User> service = new UserDAO();

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Boolean> save(@RequestBody User user){
        boolean result = service.save(user);
        return new ResponseEntity<>(result, result ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    public ResponseEntity<List<User>> listAll(){
        List<User> result = service.listAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }



}
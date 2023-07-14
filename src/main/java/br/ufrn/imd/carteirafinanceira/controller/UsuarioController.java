package br.ufrn.imd.carteirafinanceira.controller;

import br.ufrn.imd.carteirafinanceira.dao.UsuarioDAO;
import br.ufrn.imd.carteirafinanceira.model.Usuario;
import br.ufrn.imd.carteirafinanceira.service.CrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UsuarioController {

    private final CrudService<Usuario> service = new UsuarioDAO();

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Boolean> salvar(@RequestBody Usuario usuario){
        boolean result = service.save(usuario);
        return new ResponseEntity<>(result, result ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarTodos(){
        List<Usuario> result = service.listAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}

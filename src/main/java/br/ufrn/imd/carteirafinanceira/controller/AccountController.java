package br.ufrn.imd.carteirafinanceira.controller;

import br.ufrn.imd.carteirafinanceira.dao.ContaDAO;
import br.ufrn.imd.carteirafinanceira.dao.UserDAO;
import br.ufrn.imd.carteirafinanceira.model.Account;
import br.ufrn.imd.carteirafinanceira.model.User;
import br.ufrn.imd.carteirafinanceira.service.CrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/conta")
public class AccountController {

    private final ContaDAO service = new ContaDAO();
    private final CrudService<User> usuarioService = new UserDAO();

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Boolean> salvar(@RequestBody Account account){
        boolean result = service.salvar(account);
        return new ResponseEntity<>(result, result ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/usuario/{cpf}")
    public ResponseEntity<?> listarPorCpf(@PathVariable String cpf){
        record DtoConta(String nome, String instituicao, double saldo){}
        record ResponseDto(String nome, String cpf, List<DtoConta> contas){}

        User user = usuarioService.get(cpf);

        List<DtoConta> dtoContas = new ArrayList<>();
        service.listarContasPorCpf(cpf).forEach(account -> {
            dtoContas.add(new DtoConta(account.getNome(), account.getInstituicao(), account.getSaldo()));
        });

        return new ResponseEntity<>(new ResponseDto(user.getNome(), user.getCpf(), dtoContas), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Account> buscarPorId(@PathVariable Integer id){
        Account account = service.get(id);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

}

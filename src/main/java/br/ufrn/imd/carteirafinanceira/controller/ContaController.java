package br.ufrn.imd.carteirafinanceira.controller;

import br.ufrn.imd.carteirafinanceira.dao.ContaDAO;
import br.ufrn.imd.carteirafinanceira.dao.UsuarioDAO;
import br.ufrn.imd.carteirafinanceira.model.Conta;
import br.ufrn.imd.carteirafinanceira.model.Usuario;
import br.ufrn.imd.carteirafinanceira.service.CrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/conta")
public class ContaController {

    private final ContaDAO service = new ContaDAO();
    private final CrudService<Usuario> usuarioService = new UsuarioDAO();

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Boolean> salvar(@RequestBody Conta conta){
        boolean result = service.salvar(conta);
        return new ResponseEntity<>(result, result ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/usuario/{cpf}")
    public ResponseEntity<?> listarPorCpf(@PathVariable String cpf){
        record DtoConta(String nome, String instituicao, double saldo){}
        record ResponseDto(String nome, String cpf, List<DtoConta> contas){}

        Usuario usuario = usuarioService.get(cpf);

        List<DtoConta> dtoContas = new ArrayList<>();
        service.listarContasPorCpf(cpf).forEach(conta -> {
            dtoContas.add(new DtoConta(conta.getNome(), conta.getInstituicao(), conta.getSaldo()));
        });

        return new ResponseEntity<>(new ResponseDto(usuario.getNome(), usuario.getCpf(), dtoContas), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Conta> buscarPorId(@PathVariable Integer id){
        Conta conta = service.get(id);
        return new ResponseEntity<>(conta, HttpStatus.OK);
    }

}

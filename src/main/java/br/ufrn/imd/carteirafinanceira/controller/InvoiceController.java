package br.ufrn.imd.carteirafinanceira.controller;

import br.ufrn.imd.carteirafinanceira.dao.InvoiceDAO;
import br.ufrn.imd.carteirafinanceira.model.Invoice;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/fatura")

public class InvoiceController {
    InvoiceDAO service = new InvoiceDAO();

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Boolean> save(@RequestBody Invoice invoice){
        boolean result = false;
        try {
            result = service.save(invoice);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(result, result ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @PutMapping
    public  ResponseEntity<Boolean> updateCard(@RequestBody Invoice invoice){
        boolean result = service.update(invoice);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Invoice>> listAll(){
        List<Invoice> result = service.listAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("busca")
    public ResponseEntity<Invoice> get(@RequestParam Map<String, String> params){
        Invoice result = service.get(getInvoice(params));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<Boolean> delete(@RequestParam Map<String, String> params){
        Invoice invoice = service.get(getInvoice(params));
        boolean result = service.delete(invoice);
        return new ResponseEntity<>(result, result ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    public Invoice getInvoice(Map<String, String> values){
        int mes = Integer.parseInt(values.get("mes"));
        int ano = Integer.parseInt(values.get("ano"));
        int cartao = Integer.parseInt(values.get("cartao"));
        return service.get(new Invoice(mes, ano, cartao, 0));
    }
}

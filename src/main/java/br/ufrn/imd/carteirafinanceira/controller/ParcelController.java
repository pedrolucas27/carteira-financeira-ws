package br.ufrn.imd.carteirafinanceira.controller;
import br.ufrn.imd.carteirafinanceira.dao.ParcelDAO;
import br.ufrn.imd.carteirafinanceira.model.Parcel;
import br.ufrn.imd.carteirafinanceira.service.CrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/parcela")
public class ParcelController {
    private final CrudService<Parcel> service = new ParcelDAO();

    @GetMapping
    public ResponseEntity<List<Parcel>> listAll(){
        List<Parcel> result = service.listAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}

package br.ufrn.imd.carteirafinanceira.dao;

import br.ufrn.imd.carteirafinanceira.model.Parcel;
import br.ufrn.imd.carteirafinanceira.service.CrudService;

import java.util.List;

public class ParcelDAO implements CrudService<Parcel> {
    @Override
    public Parcel get(Object columnValue) {
        return null;
    }

    @Override
    public boolean save(Parcel item) {
        return false;
    }

    @Override
    public boolean delete(Parcel item) {
        return false;
    }

    @Override
    public List<Parcel> listAll() {
        return null;
    }

    @Override
    public boolean update(Parcel item) {
        return false;
    }
}

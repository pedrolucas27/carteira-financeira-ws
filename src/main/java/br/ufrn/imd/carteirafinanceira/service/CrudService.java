package br.ufrn.imd.carteirafinanceira.service;

import java.util.List;

public interface CrudService<T> {

    T get(Object columnValue);

    boolean save(T item);

    boolean delete(T item);

    List<T> listAll();

    boolean update(T item);


}

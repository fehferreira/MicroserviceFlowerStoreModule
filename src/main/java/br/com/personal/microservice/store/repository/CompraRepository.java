package br.com.personal.microservice.store.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.personal.microservice.store.model.Compra;

@Repository
public interface CompraRepository extends CrudRepository<Compra, Long> {

}

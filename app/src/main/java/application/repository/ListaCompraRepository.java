package application.repository;

import org.springframework.data.repository.CrudRepository;

import application.model.ListaCompra;

public interface ListaCompraRepository extends CrudRepository<ListaCompra, Long> {
    
}

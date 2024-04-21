package application.repository;

import org.springframework.data.repository.CrudRepository;

import application.model.Comprovante;

public interface ComprovanteRepository extends CrudRepository<Comprovante, Long> {
    
}

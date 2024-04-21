package application.repository;

import org.springframework.data.repository.CrudRepository;

import application.model.Participante;

public interface ParticipanteRepository extends CrudRepository<Participante, Long> {
    
}

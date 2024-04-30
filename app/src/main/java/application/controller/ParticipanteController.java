package application.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import application.model.Participante;
import application.repository.ParticipanteRepository;

@RestController
@RequestMapping("/participantes")
public class ParticipanteController {
    @Autowired
    private ParticipanteRepository participanteRepo;

    @GetMapping
    public Iterable<Participante> getAll() {
        return participanteRepo.findAll();
    }

    @GetMapping("/{id}")
    public Participante getOne(@PathVariable long id) {
        Optional<Participante> result = participanteRepo.findById(id);
        if(result.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Participante não encontrado"
            );
        }
        return result.get();
    }

    @PostMapping
    private Participante post(@RequestBody Participante participante) {
        return participanteRepo.save(participante);
    }
}

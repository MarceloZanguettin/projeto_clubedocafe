package application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

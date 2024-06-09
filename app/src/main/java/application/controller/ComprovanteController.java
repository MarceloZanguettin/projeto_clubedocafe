package application.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import application.model.Comprovante;
import application.model.Participante;
import application.repository.ComprovanteRepository;

@RestController
@RequestMapping("/comprovantes")
public class ComprovanteController {
    @Autowired
    private ComprovanteRepository comprovanteRepo;

    @Autowired
    private Participante participanteRepo;

    @GetMapping
    public Iterable<Comprovante> getAll() {
        return comprovanteRepo.findAll();
    }

    @GetMapping("/{id}")
    public Comprovante getOne(@PathVariable long id) {
        Optional<Comprovante> result = comprovanteRepo.findById(id);
        if(result.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Comprovante não encontrado"
            );
        }

        return result.get();
    }

    @PostMapping
    public Comprovante post(@RequestBody Comprovante comprovante) {
        if(!participanteRepo.existById(comprovante.getParticipante().getId())) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Participante vinculado não encontrado"
            );
        }
        return comprovanteRepo.save(comprovante);
    }

    @PutMapping("/{id}")
    public Comprovante put(@RequestBody Comprovante comprovante, @PathVariable long id) {
        Optional<Comprovante> result = comprovanteRepo.findById(id);
        if(result.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Comprovante não encontrado"
            );
        }

        if(!participanteRepo.existById(comprovante.getParticipante().getId())) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Participante vinculado não encontrado"
            );
        }
        result.get().setParticipante(comprovante.getParticipante());
        result.get().setValor(comprovante.getValor());
        result.get().setMes(comprovante.getMes());
        result.get().setDataPagamento(comprovante.getDataPagamento());
        return comprovanteRepo.save(result.get());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        if(comprovanteRepo.existsById(id)) {
            comprovanteRepo.deleteById(id);
        } else {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Comprovante não encontrado"
            );
        }
    }
}

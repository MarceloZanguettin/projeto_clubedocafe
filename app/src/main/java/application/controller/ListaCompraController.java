package application.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import application.model.CompraPossuiProduto;
import application.model.ListaCompra;
import application.repository.ListaCompraRepository;

@RestController
@RequestMapping("/listacompras")
public class ListaCompraController {
    @Autowired
    private ListaCompraRepository listaCompraRepo;

    @Autowired
    private CompraPossuiProduto compraPossuiProdutoRepo;

    @GetMapping
    public Iterable<ListaCompra> getAll() {
        return listaCompraRepo.findAll();
    }

    @GetMapping("/{id}")
    public ListaCompra getOne(@PathVariable long id) {
        Optional<ListaCompra> result = listaCompraRepo.findById(id);
        if(result.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lista de Compra não Encontrada");
        }
        return result.get();
    }

    @PostMapping
    public ListaCompra post(@RequestBody ListaCompra listaCompra) {
        if(!compraPossuiProdutoRepo.existsById(listaCompra.getCompraPossiProduto().getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Lista de Compra vinculada não encontrada");
        }
        return listaCompraRepo.save(listaCompra);
    }

    @PutMapping
    public ListaCompra put(@RequestBody ListaCompra listaCompra, @PathVariable long id) {
        Optional<ListaCompra> result = listaCompraRepo.findById(id);

        if(result.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Lista de Compra não Encontrada"
            );
        }
        if(!compraPossuiProdutoRepo.existsById(listaCompra.getCompraPossiProduto().getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Lista de Compra vinculada não encontrada");
        }
        result.get().setDataCompra(listaCompra.getDataCompra());
        result.get().setCompraPossiProduto(listaCompra.getCompraPossiProduto());
        return listaCompraRepo.save(result.get());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        if(listaCompraRepo.existsById(id)) {
            listaCompraRepo.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lista de Compra não Encontrada");
        }
    }
}

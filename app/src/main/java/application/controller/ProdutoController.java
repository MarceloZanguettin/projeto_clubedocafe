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


import application.model.CompraPossuiProduto;
import application.model.Produto;
import application.repository.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoRepository produtoRepo;
    
    @Autowired
    private CompraPossuiProduto compraPossuiProdutoRepo;

    @GetMapping
    public Iterable<Produto> getAll() {
        return produtoRepo.findAll();
    }

    @GetMapping("/{id}")
    public Produto getOne(@PathVariable long id) {
        Optional<Produto> result = produtoRepo.findById(id);
        if(result.isEmpty()) {
            throw new ResponseStatusException (
                HttpStatus.NOT_FOUND, "Produto não Encontrado"
            );
        }
        return result.get();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        if(produtoRepo.existById(id)) {
            produtoRepo.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não Encontrado");
        }
    }

    @PostMapping//
    public Produto post(@RequestBody Produto produto) {
        if(!compraPossuiProdutoRepo.existById(produto.getCompraPossiProduto().getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Compra possu Produto vinculada não Encontrada");
        }
        return produtoRepo.save(produto);
    }

    @PutMapping("/{id}")
    public Produto put(@RequestBody Produto produto, @PathVariable long id) {
        Optional<Produto> result = produtoRepo.findById(id);
        if(result.isEmpty()) {
            throw new ResponseStatusException (
                HttpStatus.NOT_FOUND, "Produto não Encontrado"
            );
        }
        if(!compraPossuiProdutoRepo.existById(produto.getCompraPossiProduto().getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Compra possu Produto vinculada não Encontrada");
        }
        result.get().setCompraPossiProduto(produto.getCompraPossiProduto());
        result.get().setDescricao(produto.getDescricao());
        result.get().setMarca(produto.getMarca());

        return produtoRepo.save(result.get());
    }
}

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
import application.model.ListaCompra;
import application.model.Produto;

@RestController
@RequestMapping("/comprapossuiprodutos")
public class CompraPossuiProdutoController {
    @Autowired
    private CompraPossuiProduto compraPossuiProdutoRepo;

    @Autowired
    private ListaCompra listaCompraRepo;

    @Autowired
    private Produto produtoRepo;

    @GetMapping
    public Iterable<CompraPossuiProduto> getAll() {
        return compraPossuiProdutoRepo.findAll();
    }

    @GetMapping("/{id}")
    public CompraPossuiProduto getOne(@PathVariable long id) {
        Optional<CompraPossuiProduto> result = compraPossuiProdutoRepo.findById(id);
        if(result.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Compra possui Produto não Encontrada"
            );
        }
        return result.get();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        if(compraPossuiProdutoRepo.existById(id)) {
            compraPossuiProdutoRepo.deleteById(id);
        } else {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Compra possui Produto não Encontrada"
            );
        }
    }

    @PostMapping
    public CompraPossuiProduto post(@RequestBody CompraPossuiProduto compraPossuiProduto) {
        if(!listaCompraRepo.existById(CompraPossuiProduto.getListaCompra())) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Lista de Compra vinculada não Encontrada"
            );
        } else if(!produtoRepo.existById(CompraPossuiProduto.getProduto())){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Poduto vinculado não Encontrado"
            );
        }
        return compraPossuiProdutoRepo.save(compraPossuiProduto);
    }

    @PutMapping("/{id}")
    public CompraPossuiProduto put(@RequestBody CompraPossuiProduto compraPossuiProduto, @PathVariable long id) {
        Optional<CompraPossuiProduto> result = compraPossuiProdutoRepo.findById(id);
        if(result.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Compra possui Produto não Encontrada"
            );
        }
        if(!listaCompraRepo.existById(CompraPossuiProduto.getListaCompra())) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Lista de Compra vinculada não Encontrada"
            );
        } else if(!produtoRepo.existById(CompraPossuiProduto.getProduto())){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Poduto vinculado não Encontrado"
            );
        }

        result.get().setListaCompra(compraPossuiProduto.getListaCompra());
        result.get().setProduto(compraPossuiProduto.getProduto());
        result.get().setQuantidade(compraPossuiProduto.getQuantidade());
        result.get().setValor(compraPossuiProduto.getValor());

        return compraPossuiProdutoRepo.save(result.get());
    }
}

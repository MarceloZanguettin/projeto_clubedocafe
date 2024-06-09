package application.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import application.model.CompraPossuiProduto;

@RestController
@RequestMapping("/comprapossuiprodutos")
public class CompraPossuiProdutoController {
    @Autowired
    private CompraPossuiProduto compraPossuiProdutoRepo;

    @Autowired
    private 
}

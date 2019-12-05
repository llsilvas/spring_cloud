package br.com.app.produto.controllers;

import br.com.app.produto.models.entity.Produto;
import br.com.app.produto.models.service.IProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProdutoController {
    @Autowired
    private IProdutoService produtoService;

    @GetMapping("/listar")
    private List<Produto> listar(){
        return produtoService.findAll();
    }

    @GetMapping("/ver/{id}")
    public Produto detalhe(@PathVariable Long id){
        return produtoService.findById(id);
    }
}

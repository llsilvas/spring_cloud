package br.com.app.produto.controllers;

import br.com.app.produto.models.entity.Produto;
import br.com.app.produto.models.service.IProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProdutoController {
    @Autowired
    private Environment environment;

    @Value("${server.port}")
    private Integer port;

    @Autowired
    private IProdutoService produtoService;

    @GetMapping("/listar")
    private List<Produto> listar(){
        return produtoService.findAll().stream().map(produto -> {
           // produto.setPort(Integer.valueOf(environment.getProperty("server.port")));
            produto.setPort(port);
            return produto;
        }).collect(Collectors.toList());
    }

    @GetMapping("/ver/{id}")
    public Produto detalhe(@PathVariable Long id){

        Produto produto = produtoService.findById(id);
        //produto.setPort(Integer.valueOf(environment.getProperty("server.port")));
        produto.setPort(port);

//        try {
//            Thread.sleep(2000L);
//        }catch (InterruptedException e){
//            e.printStackTrace();
//        }
        return produto;
    }
}

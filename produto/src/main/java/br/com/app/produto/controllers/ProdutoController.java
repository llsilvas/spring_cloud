package br.com.app.produto.controllers;

import br.com.app.produto.models.entity.Produto;
import br.com.app.produto.models.service.IProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
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

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        produtoService.deleteById(id);
    }

    @PutMapping("/editar/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Produto editar(@RequestBody Produto produto, @PathVariable Long id){
        Produto produto1 = produtoService.findById(id);

        produto1.setNome(produto.getNome());
        produto1.setPreco(produto.getPreco());

        return produtoService.save(produto1);
    }

    @PostMapping("/criar")
    @ResponseStatus(HttpStatus.CREATED)
    public Produto criar(@RequestBody Produto produto){
        return produtoService.save(produto);
    }
}

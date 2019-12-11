package br.com.app.item.controllers;

import br.com.app.item.models.Item;
import br.com.app.item.models.Produto;
import br.com.app.item.service.IItemService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemController {
    @Autowired
    @Qualifier("serviceFeign")
    private IItemService itemService;

    @GetMapping("/listar")
    public List<Item> listar(){
        return itemService.findAll();
    }

    @HystrixCommand(fallbackMethod = "metodoAlternativo")
    @GetMapping("/ver/{id}/quantidade/{quantidade}")
    public Item detalhe(@PathVariable Long id, @PathVariable Integer quantidade){
        return itemService.findById(id, quantidade);
    }

    public Item metodoAlternativo(Long id, Integer quantidade){
        return Item.builder()
                .quantidade(10)
                .produto(Produto.builder()
                        .id(123l)
                        .nome("Teste")
                        .preco(500.00).build()).build();
    }


}

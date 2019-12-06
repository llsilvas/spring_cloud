package br.com.app.item.controllers;

import br.com.app.item.models.Item;
import br.com.app.item.service.IItemService;
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
    private List<Item> listar(){
        return itemService.findAll();
    }

    @GetMapping("/ver/{id}/quantidade/{quantidade}")
    public Item detalhe(@PathVariable Long id, @PathVariable Integer quantidade){
        return itemService.findById(id, quantidade);
    }



}

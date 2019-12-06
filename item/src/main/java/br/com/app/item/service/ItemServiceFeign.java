package br.com.app.item.service;

import br.com.app.item.clientes.ProdutoClientRest;
import br.com.app.item.models.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("serviceFeign")
@Primary
public class ItemServiceFeign implements IItemService{

    @Autowired
    private ProdutoClientRest clientRest;

    @Override
    public List<Item> findAll() {
        return clientRest.listar().stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer quantidade) {
        return new Item(clientRest.detalhe(id), quantidade);
    }
}

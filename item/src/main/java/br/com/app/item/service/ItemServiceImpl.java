package br.com.app.item.service;

import br.com.app.item.models.Item;
import br.com.app.item.models.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements IItemService{

    @Autowired
    private RestTemplate clientRest;

    @Override
    public List<Item> findAll() {
        List<Produto> produtos = Arrays.asList(clientRest.getForObject("http://localhost:8001/listar", Produto[].class));

        return produtos.stream().map(produto -> new Item(produto, 1)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer quantidade) {
        Map<String, String> pathVariales = new HashMap<String, String>();
        pathVariales.put("id", id.toString());
        Produto produto = clientRest.getForObject("http://localhost:8001/ver/{id}", Produto.class, pathVariales);
        return new Item(produto, quantidade);
    }
}

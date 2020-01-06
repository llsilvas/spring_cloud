package br.com.app.item.service;

import br.com.app.commons.models.entity.Produto;
import br.com.app.item.models.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("itemService")
public class ItemServiceImpl implements IItemService {

    @Autowired
    private RestTemplate clientRest;

    @Override
    public List<Item> findAll() {
        List<Produto> produtos = Arrays.asList(clientRest.getForObject("http://produto/listar", Produto[].class));

        return produtos.stream().map(produto -> new Item(produto, 1)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer quantidade) {
        Map<String, String> pathVariales = new HashMap<String, String>();
        pathVariales.put("id", id.toString());
        Produto produto = clientRest.getForObject("http://produto:8001/ver/{id}", Produto.class, pathVariales);
        return new Item(produto, quantidade);
    }

    @Override
    public Produto save(Produto produto) {
        HttpEntity<Produto> body = new HttpEntity<Produto>(produto);
        ResponseEntity<Produto> response = clientRest.exchange("http://produto:8001/criar", HttpMethod.POST, body, Produto.class);

        return response.getBody();
    }

    @Override
    public Produto update(Produto produto, Long id) {
        Map<String, String> pathVariales = new HashMap<String, String>();
        pathVariales.put("id", id.toString());
        HttpEntity<Produto> body = new HttpEntity<Produto>(produto);
        ResponseEntity<Produto> response = clientRest.exchange("http://produto/editar/{id}", HttpMethod.PUT, body, Produto.class, pathVariales);
        return response.getBody();
    }

    @Override
    public void delete(Long id) {
        Map<String, String> pathVariales = new HashMap<String, String>();
        pathVariales.put("id", id.toString());
        clientRest.delete("http://produto/delete/{id}", pathVariales);
    }
}

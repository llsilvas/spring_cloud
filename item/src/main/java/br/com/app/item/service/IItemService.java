package br.com.app.item.service;

import br.com.app.commons.models.entity.Produto;
import br.com.app.item.models.Item;

import java.util.List;

public interface IItemService {

    public List<Item> findAll();

    public Item findById(Long id, Integer quantidade);

    public Produto save(Produto produto);

    public Produto update(Produto produto, Long id);

    public void delete(Long id);

}

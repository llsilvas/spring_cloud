package br.com.app.item.service;

import br.com.app.item.models.Item;

import java.util.List;

public interface IItemService {

    public List<Item> findAll();

    public Item findById(Long id, Integer quantidade);

}

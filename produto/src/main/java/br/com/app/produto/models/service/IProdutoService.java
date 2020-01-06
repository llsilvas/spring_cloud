package br.com.app.produto.models.service;


import br.com.app.commons.models.entity.Produto;

import java.util.List;

public interface IProdutoService {

    public List<Produto> findAll();
    public Produto findById(Long id);

    public Produto save(Produto produto);

    public void deleteById(Long id);
}

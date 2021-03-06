package br.com.app.produto.models.service;

import br.com.app.commons.models.entity.Produto;
import br.com.app.produto.models.dao.ProdutoDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProdutoServiceImpl implements IProdutoService {

    @Autowired
    private ProdutoDao produtoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Produto> findAll() {
        return (List<Produto>) produtoDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Produto findById(Long id) {
        return produtoDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Produto save(Produto produto) {
        return produtoDao.save(produto);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        produtoDao.deleteById(id);
    }
}

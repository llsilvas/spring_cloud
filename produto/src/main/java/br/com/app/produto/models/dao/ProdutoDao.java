package br.com.app.produto.models.dao;

import br.com.app.produto.models.entity.Produto;
import org.springframework.data.repository.CrudRepository;

public interface ProdutoDao extends CrudRepository<Produto, Long> {
}

package br.com.app.item.clientes;

import br.com.app.item.models.Produto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "produto", url = "localhost:8001")
public interface ProdutoClientRest {

    @GetMapping("/listar")
    public List<Produto> listar();

    @GetMapping("/ver/{id}")
    public Produto detalhe(@PathVariable Long id);
}

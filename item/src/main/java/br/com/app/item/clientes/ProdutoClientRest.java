package br.com.app.item.clientes;

import br.com.app.item.models.Produto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "produto")
public interface ProdutoClientRest {

    @GetMapping("/listar")
    public List<Produto> listar();

    @GetMapping("/ver/{id}")
    public Produto detalhe(@PathVariable Long id);

    @PostMapping("/criar")
    public Produto criar(@RequestBody Produto produto);

    @PutMapping("/editar/{id}")
    public Produto editar(@RequestBody Produto produto, @PathVariable Long id);

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id);
}

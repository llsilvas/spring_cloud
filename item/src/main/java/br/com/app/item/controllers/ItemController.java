package br.com.app.item.controllers;

import br.com.app.commons.models.entity.Produto;
import br.com.app.item.models.Item;
import br.com.app.item.service.IItemService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RefreshScope
@RestController
public class ItemController {
    @Autowired
    @Qualifier("serviceFeign")
    private IItemService itemService;

    private static Logger log = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    private Environment environment;

    @Value("${configuracao.texto}")
    private String texto;

    @GetMapping("/listar")
    public List<Item> listar(){
        return itemService.findAll();
    }

    @HystrixCommand(fallbackMethod = "metodoAlternativo")
    @GetMapping("/ver/{id}/quantidade/{quantidade}")
    public Item detalhe(@PathVariable Long id, @PathVariable Integer quantidade){
        return itemService.findById(id, quantidade);
    }

    public Item metodoAlternativo(Long id, Integer quantidade){
        return Item.builder()
                .quantidade(10)
                .produto(Produto.builder()
                        .id(123l)
                        .nome("Teste")
                        .preco(500.00).build()).build();
    }


    @GetMapping("/obter-config")
    public ResponseEntity<?> obterConfig(){

        log.info(texto);
        Map<String, String> json = new HashMap<>();
        json.put("texto", texto);

        if(environment.getActiveProfiles().length> 0 && environment.getActiveProfiles()[0].equals("dev")){
            json.put("autor.nome", environment.getProperty("configuracao.autor.nome"));
        }

        return new ResponseEntity<Map<String, String>>(json, HttpStatus.OK);
    }

    @PostMapping("/criar")
    @ResponseStatus(HttpStatus.CREATED)
    public Produto criar(@RequestBody Produto produto){
        return itemService.save(produto);
    }

    @PutMapping("/editar/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Produto editar(@RequestBody Produto produto, @PathVariable Long id){
        return itemService.update(produto, id);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        itemService.delete(id);
    }

}

package br.com.app.item.models;

import br.com.app.commons.models.entity.Produto;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    private Produto produto;
    private Integer quantidade;

    public Double getTotal(){
        return produto.getPreco() * quantidade.doubleValue();
    }
}

package br.com.app.item.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    private Produto produto;
    private Integer quantidade;

    public Double getTotal(){
        return produto.getPreco() * quantidade.doubleValue();
    }
}

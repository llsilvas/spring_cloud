package br.com.app.item.models;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Produto implements Serializable {

    private Long id;
    private String nome;
    private Double preco;
    private Date criadoEm;

}

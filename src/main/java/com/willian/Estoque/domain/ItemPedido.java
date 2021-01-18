package com.willian.Estoque.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class ItemPedido implements Serializable {

    @JsonIgnore
    @EmbeddedId
    private ItemPedidoPk item = new ItemPedidoPk();

    private Integer quantidade;

    public ItemPedido(Pedido pedido,
                      Produto produto,
                      Integer quantidade){
        super();
        item.setPedido(pedido);
        item.setProduto(produto);
        this.quantidade = quantidade;
    }

    @JsonIgnore
    public Pedido getPedido(){
        return item.getPedido();
    }

    public Produto getProduto(){
        return item.getProduto();
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
    
}

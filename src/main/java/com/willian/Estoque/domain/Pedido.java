package com.willian.Estoque.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm" , locale = "pt-BR", timezone = "Brazil/East")
    private Date dataInicio;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date dataFim;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "item.pedido")
    private List<ItemPedido> itens = new ArrayList<>();

    public List<Produto> getProdutos(){
        List<Produto> lista = new ArrayList<>();
        for (ItemPedido itemPedido : itens ) {
            lista.add(itemPedido.getProduto());
        }
        return lista;
    }
}

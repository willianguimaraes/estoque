package com.willian.Estoque.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;
    private String nome;
    private String email;
    private String cpfCnpj;

    @JsonIgnore
    private String senha;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario")
    private List<Pedido> pedidos = new ArrayList<>();
}

package com.willian.Estoque.service;

import com.willian.Estoque.domain.Categoria;
import com.willian.Estoque.domain.Produto;
import com.willian.Estoque.exceptions.ObjectNotFoundException;
import com.willian.Estoque.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto findById(Integer id){
        Optional<Produto> produto = produtoRepository.findById(id);

        return produto.orElseThrow(() -> new ObjectNotFoundException(
                "Nao Encontrado id "+ id +", do tipo " + Produto.class.getName()));
    }

}

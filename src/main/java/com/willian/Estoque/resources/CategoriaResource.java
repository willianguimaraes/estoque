package com.willian.Estoque.resources;

import com.willian.Estoque.domain.Categoria;
import com.willian.Estoque.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/categoria")
public class CategoriaResource {

        @Autowired
        private CategoriaService categoriaService;

        @RequestMapping(value = "/{id}")
        public ResponseEntity<?> findbyId(@PathVariable Integer id){
            Categoria categoria = categoriaService.findById(id);
            return ResponseEntity.ok().body(categoria);
        }

}

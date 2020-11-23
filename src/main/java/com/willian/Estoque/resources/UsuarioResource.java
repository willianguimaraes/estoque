package com.willian.Estoque.resources;

import com.willian.Estoque.domain.Usuario;
import com.willian.Estoque.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioResource {

    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> buscaPorId(@PathVariable Integer id){

        Usuario usuario = usuarioService.findById(id);

        return ResponseEntity.ok().body(usuario);
    }
}

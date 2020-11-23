package com.willian.Estoque.service;

import com.willian.Estoque.exceptions.ObjectNotFoundException;
import com.willian.Estoque.domain.Usuario;
import com.willian.Estoque.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario findById(Integer id){
        Optional<Usuario> usuario = usuarioRepository.findById(id);

        return usuario.orElseThrow(() -> new ObjectNotFoundException(
                "Nao Encontrado id "+ id +", do tipo " + Usuario.class.getName()));
    }
}

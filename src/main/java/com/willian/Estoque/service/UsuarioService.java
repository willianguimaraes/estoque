package com.willian.Estoque.service;

import com.willian.Estoque.exceptions.DataIntegrityException;
import com.willian.Estoque.exceptions.ObjectNotFoundException;
import com.willian.Estoque.domain.Usuario;
import com.willian.Estoque.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<Usuario> findAll(){
        return usuarioRepository.findAll();
    }

    public Page<Usuario> findPage(Integer page,
                                  Integer linesPerPage,
                                  String order,
                                  String direction){

        PageRequest pageRequest = PageRequest.of(page,
                linesPerPage,
                Sort.Direction.valueOf(direction),
                order);
        return usuarioRepository.findAll(pageRequest);
    }

    public Usuario insertUsuario(Usuario obj){
        obj.setId(null);
        return usuarioRepository.save(obj);
    }

    public Usuario updateUsuario(Usuario obj){
        findById(obj.getId());
        return usuarioRepository.save(obj);
    }

    public void deleteUsuario(Integer id){
        findById(id);
        try{
            usuarioRepository.deleteById(id);
        }catch (DataIntegrityException ex){
            throw new DataIntegrityException("Nao é possivel exclusao, com pedido ativo");
        }
    }

}

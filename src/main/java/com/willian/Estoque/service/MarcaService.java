package com.willian.Estoque.service;

import com.willian.Estoque.domain.Categoria;
import com.willian.Estoque.domain.Cliente;
import com.willian.Estoque.domain.Marca;
import com.willian.Estoque.exceptions.DataIntegrityException;
import com.willian.Estoque.exceptions.ObjectNotFoundException;
import com.willian.Estoque.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

    public Marca findById(Integer id){
        Optional<Marca> marca = marcaRepository.findById(id);

        return marca.orElseThrow( () -> new ObjectNotFoundException(
                "Nao Encontrado id "+ id +", do tipo " + Marca.class.getName()));
    }

    public List<Marca> findAll(){
        return marcaRepository.findAll();
    }

    public Page<Marca> findPage(Integer page,
                                  Integer linesPerPage,
                                  String order,
                                  String direction){

        PageRequest pageRequest = PageRequest.of(page,
                linesPerPage,
                Sort.Direction.valueOf(direction),
                order);
        return marcaRepository.findAll(pageRequest);
    }

    public Marca insertMarca(Marca obj){
        obj.setId(null);
        return marcaRepository.save(obj);
    }

    public Marca updateMarca(Marca obj){
        findById(obj.getId());
        return marcaRepository.save(obj);
    }

    public void deleteMarca(Integer id){
        findById(id);
        try{
            marcaRepository.deleteById(id);
        }catch (DataIntegrityException ex){
            throw new DataIntegrityException("Nao é possivel exclusao");
        }
    }
}

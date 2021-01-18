package com.willian.Estoque.service;

import com.willian.Estoque.domain.Categoria;
import com.willian.Estoque.domain.Cliente;
import com.willian.Estoque.domain.Marca;
import com.willian.Estoque.exceptions.DataIntegrityException;
import com.willian.Estoque.exceptions.ObjectNotFoundException;
import com.willian.Estoque.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente findById(Integer id){
        Optional<Cliente> cliente = clienteRepository.findById(id);

        return cliente.orElseThrow(() -> new ObjectNotFoundException(
                "Nao Encontrado id "+ id +", do tipo " + Cliente.class.getName()));
    }

    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }

    public Page<Cliente> findPage(Integer page,
                                  Integer linesPerPage,
                                  String order,
                                  String direction){

        PageRequest pageRequest = PageRequest.of(page,
                linesPerPage,
                Sort.Direction.valueOf(direction),
                order);
        return clienteRepository.findAll(pageRequest);
    }

    public Cliente insertCliente(Cliente obj){
        obj.setId(null);
        return clienteRepository.save(obj);
    }

    public Cliente updateCliente(Cliente obj){
        findById(obj.getId());
        return clienteRepository.save(obj);
    }

    public void deleteCliente(Integer id){
        findById(id);
        try{
            clienteRepository.deleteById(id);
        }catch (DataIntegrityException ex){
            throw new DataIntegrityException("Nao é possivel exclusao, com pedido ativo");
        }
    }
}

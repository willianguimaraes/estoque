package com.willian.Estoque.service;

import com.willian.Estoque.domain.Pedido;
import com.willian.Estoque.exceptions.ObjectNotFoundException;
import com.willian.Estoque.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

        @Autowired
        private PedidoRepository pedidoRepository;

        public Pedido findById(Integer id){
            Optional<Pedido> pedido = pedidoRepository.findById(id);

            return pedido.orElseThrow(() -> new ObjectNotFoundException(
                    "Nao Encontrado id "+ id +", do tipo " + Pedido.class.getName()));
        }

        public List<Pedido> findAll(){
            return pedidoRepository.findAll();
    }

    public Page<Pedido> findPage(Integer page,
                                Integer linesPerPage,
                                String order,
                                String direction){

        PageRequest pageRequest = PageRequest.of(page,
                linesPerPage,
                Sort.Direction.valueOf(direction),
                order);
        return pedidoRepository.findAll(pageRequest);
    }

    public Pedido insertPedido(Pedido obj){
        obj.setId(null);
        return pedidoRepository.save(obj);
    }

    public Pedido updatePedido(Pedido obj){
            findById(obj.getId());
            return pedidoRepository.save(obj);
    }
}

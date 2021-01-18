package com.willian.Estoque.resources;

import com.willian.Estoque.domain.Marca;
import com.willian.Estoque.domain.Pedido;
import com.willian.Estoque.repository.PedidoRepository;
import com.willian.Estoque.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/pedido")
public class PedidoResource {

    @Autowired
    private PedidoService pedidoService;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<Pedido> findById(@PathVariable Integer id){
        Pedido obj = pedidoService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Pedido>> findAll(){
        List<Pedido> lista = pedidoService.findAll();
        return ResponseEntity.ok().body(lista);
    }

    @RequestMapping(value = "/page",method = RequestMethod.GET)
    public ResponseEntity<Page<Pedido>> findPage(
            @RequestParam(value="page", defaultValue = "0") Integer page,
            @RequestParam(value="linesOfPage", defaultValue = "25") Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(value="direction", defaultValue = "ASC") String direction){
        Page<Pedido> lista = pedidoService.findPage(page,linesPerPage,orderBy,direction);

        return ResponseEntity.ok().body(lista);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insertMarca(@RequestBody Pedido obj){
        obj = pedidoService.insertPedido(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public ResponseEntity<Pedido> updatePedido(@RequestBody Pedido obj,
                                               @PathVariable Integer id){
        obj.setId(id);
        obj = pedidoService.updatePedido(obj);
        return ResponseEntity.ok().body(obj);
    }
}

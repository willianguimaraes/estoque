package com.willian.Estoque.resources;

import com.willian.Estoque.domain.Marca;
import com.willian.Estoque.repository.MarcaRepository;
import com.willian.Estoque.service.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/marca")
public class MarcaResource {

    @Autowired
    private MarcaService service;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<Marca> findById(@PathVariable Integer id){
        Marca obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Marca>> findAll(){
        List<Marca> lista = service.findAll();
        return ResponseEntity.ok().body(lista);
    }

    @RequestMapping(value = "/page",method = RequestMethod.GET)
    public ResponseEntity<Page<Marca>> findPage(
            @RequestParam(value="page", defaultValue = "0") Integer page,
            @RequestParam(value="linesOfPage", defaultValue = "25") Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(value="direction", defaultValue = "ASC") String direction){
        Page<Marca> lista = service.findPage(page,linesPerPage,orderBy,direction);

        return ResponseEntity.ok().body(lista);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insertMarca(@RequestBody Marca obj){
    obj = service.insertMarca(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
    return ResponseEntity.created(uri).build();
    }
}

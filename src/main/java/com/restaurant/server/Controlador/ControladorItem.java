package com.restaurant.server.Controlador;

import com.restaurant.server.dominio.DominioItem;
import com.restaurant.server.modelo.Item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("item")
@CrossOrigin(origins = "*")
public class ControladorItem {


    @Autowired
    private DominioItem itemService;

    @PostMapping("criar")
    public ResponseEntity<Item> criar(@RequestBody Item item) {

        try {
            Item itemCriado = itemService.criar(item);
            return ResponseEntity.ok().body(itemCriado);
        }
        catch(Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("buscar-todos")
    public ResponseEntity<Iterable<Item>> getAllItems() {

        try {
            Iterable<Item> itens = itemService.buscarTodos();
            return ResponseEntity.ok().body(itens);
        }
        catch(Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

    @PutMapping("/atualizar")
    public ResponseEntity<Item> updateItem(@RequestBody Item itemAtualizdo) {
        Item updated = itemService.atualizar(itemAtualizdo);
        return ResponseEntity.ok(updated);
    }
    
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Item> deleteItem(@PathVariable("id") Long id) {
        itemService.deletar(id);
        return ResponseEntity.ok().build();
    }

}

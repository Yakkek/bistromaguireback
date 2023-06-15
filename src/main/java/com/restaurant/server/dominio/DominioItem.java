package com.restaurant.server.dominio;

import com.restaurant.server.modelo.Item;
import com.restaurant.server.persistencia.ItemPersistencia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class DominioItem {

    @Autowired
    private ItemPersistencia itemPersistencia;

    public Item criar(Item item) {
        return itemPersistencia.save(item);
    }

    public List<Item> criarTodos(List<Item> items) {
        return itemPersistencia.saveAll(items);
    }

    public Item buscarItemId(Long id) {
        return itemPersistencia.findById(id).orElse(null);
    }

    public List<Item> buscarTodos() {
        return itemPersistencia.findAll();
    }

    public Item atualizar(Item item) {
        Item itemBuscado = itemPersistencia.findById(item.getId()).orElse(null);
        itemBuscado.setnome(item.getnome());
        itemBuscado.setPreco(item.getPreco());
        itemBuscado.setDescricao(item.getDescricao());
        itemBuscado.setTipo(item.getTipo());
        itemBuscado.setQuantidade(item.getQuantidade());
        return itemPersistencia.save(itemBuscado);
    }

    public void remover(Long id, Integer quantidade) {

        Item itemBuscado = itemPersistencia.findById(id).orElse(null);

        if (itemBuscado == null) {
            throw new RuntimeException("Item não encontrado");
        }

        if (itemBuscado.getQuantidade() < quantidade) {
            throw new RuntimeException("quantidade insuficiente");
        }

        Objects.requireNonNull(itemBuscado).setQuantidade(itemBuscado.getQuantidade() - quantidade);

        itemPersistencia.save(itemBuscado);

    }

    public void deletar(Long id) {

        Item itemBuscado = itemPersistencia.findById(id).orElse(null);

        if (itemBuscado == null) {
            throw new RuntimeException("Item não encontrado");
        }

        itemPersistencia.deleteById(id);
    }


}

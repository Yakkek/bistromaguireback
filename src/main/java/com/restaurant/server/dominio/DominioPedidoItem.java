package com.restaurant.server.dominio;

import com.restaurant.server.modelo.PedidoItem;
import com.restaurant.server.persistencia.PedidoItemPersistencia;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DominioPedidoItem {

    private final PedidoItemPersistencia pedidoItemPersistencia;

    public DominioPedidoItem(PedidoItemPersistencia pedidoItemPersistencia) {
        this.pedidoItemPersistencia = pedidoItemPersistencia;
    }

    public List<PedidoItem> criarTodos(List<PedidoItem> pedidoItems) {
        return pedidoItemPersistencia.saveAll(pedidoItems);
    }

    public PedidoItem criar(PedidoItem pedidoItem) {
        return pedidoItemPersistencia.save(pedidoItem);
    }

}

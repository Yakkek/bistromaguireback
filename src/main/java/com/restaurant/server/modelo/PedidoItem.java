package com.restaurant.server.modelo;

import javax.persistence.*;

@Entity
public class PedidoItem {
    @EmbeddedId
    private PedidoItemId id = new PedidoItemId();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("pedidoId")
    private Pedido pedido;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("itemId")
    private Item item;

    private int quantidade;

    public PedidoItemId getId() {
        return id;
    }

    public void setId(PedidoItemId id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}





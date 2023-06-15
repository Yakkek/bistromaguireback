package com.restaurant.server.modelo;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PedidoItemId implements Serializable {
    private Long pedidoId;
    private Long itemId;

    // contructors, getters, setters and equals/hashcode methods

    public PedidoItemId() {
    }

    public PedidoItemId(Long pedidoId, Long itemId) {
        this.pedidoId = pedidoId;
        this.itemId = itemId;
    }

    // equals and hashcode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PedidoItemId)) return false;
        PedidoItemId that = (PedidoItemId) o;
        return getPedidoId().equals(that.getPedidoId()) &&
                getItemId().equals(that.getItemId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPedidoId(), getItemId());
    }

    // getters and setters

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }



}
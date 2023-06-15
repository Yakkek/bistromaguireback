package com.restaurant.server.persistencia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurant.server.modelo.PedidoItem;
import com.restaurant.server.modelo.PedidoItemId;

@Repository
public interface PedidoItemPersistencia extends JpaRepository<PedidoItem, PedidoItemId> {

}

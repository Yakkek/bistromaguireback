package com.restaurant.server.persistencia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.restaurant.server.modelo.Pedido;
import com.restaurant.server.modelo.DTO.PedidoDTO;

import java.util.List;

@Repository
public interface PedidoPersistencia extends JpaRepository<Pedido,  Long> {

    @Query("SELECT new com.restaurant.server.modelo.DTO.PedidoDTO(o.id, o.valor, o.data, c.nome, oi.item.nome, oi.quantidade) FROM Pedido o JOIN o.cliente c JOIN o.pedidoItens oi")
    List<PedidoDTO> findOrdersWithItemsAndClient();


}

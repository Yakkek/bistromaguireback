package com.restaurant.server.modelo.DTO;

import java.util.List;

public class PedidoRequestDTO {

    private Long clienteId;
    private List<PedidoItemRequestDto> itens;

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public List<PedidoItemRequestDto> getItens() {
        return itens;
    }

    public void setItens(List<PedidoItemRequestDto> itens) {
        this.itens = itens;
    }
}

package com.restaurant.server.modelo.DTO;

public class PedidoItemRequestDto {
    private Long itemId;
    private Integer quantidade;

    public PedidoItemRequestDto() {
    }

    public PedidoItemRequestDto(Long itemId, Integer quantidade) {
        this.itemId = itemId;
        this.quantidade = quantidade;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}

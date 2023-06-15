package com.restaurant.server.modelo.DTO;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.restaurant.server.modelo.serializer.OrderDTOSerializer;

import java.util.ArrayList;
import java.util.List;

@JsonSerialize(using = OrderDTOSerializer.class)
public class PedidoDTO {


    private Long id;
    private Integer valor;
    private String data;
    private String cliente;
    private List<ItemDTO> itens = new ArrayList<>();

    public PedidoDTO(Long id ,Integer valor, String data, String cliente, String item, Integer quantidade) {
        this.id = id;
        this.valor = valor;
        this.data = data;
        this.cliente = cliente;
        this.itens.add(new ItemDTO(item, quantidade));
    }

    public PedidoDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public List<ItemDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemDTO> itens) {
        this.itens = itens;
    }
}

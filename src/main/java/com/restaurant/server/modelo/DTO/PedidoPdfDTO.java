package com.restaurant.server.modelo.DTO;

public class PedidoPdfDTO {

    private Long idPedido;
    private Integer valor;
    private String data;
    private Integer quantidadeItens;
    private String cliente;

    public PedidoPdfDTO(Long idPedido ,Integer valor, String data, Integer quantidadeItens, String cliente) {
        this.idPedido = idPedido;
        this.valor = valor;
        this.data = data;
        this.quantidadeItens = quantidadeItens;
        this.cliente = cliente;
    }

    public PedidoPdfDTO() {
    }

    public Long getidPedido() {
        return idPedido;
    }

    public void setidPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public Integer getValor() {
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

    public Integer getQuantidadeItens() {
        return quantidadeItens;
    }

    public void setQuantidadeItens(Integer quantidadeItens) {
        this.quantidadeItens = quantidadeItens;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente= cliente;
    }

}
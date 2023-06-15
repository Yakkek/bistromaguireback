package com.restaurant.server.modelo.DTO;

public class ItemDTO {

    private String nome;
    private Integer quantidade;

    public ItemDTO(String nome, Integer quantidade) {
        this.nome = nome;
        this.quantidade = quantidade;
    }

    public ItemDTO() {
    }

    public String getNome() {
        return nome;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

}

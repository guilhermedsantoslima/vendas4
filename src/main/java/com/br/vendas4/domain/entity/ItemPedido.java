package com.br.vendas4.domain.entity;

public class ItemPedido {
    private Integer id;
    private Pedido pedidio;
    private Produto produto;
    private Integer quantidade;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Pedido getPedidio() {
        return pedidio;
    }

    public void setPedidio(Pedido pedidio) {
        this.pedidio = pedidio;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}

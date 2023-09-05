package com.br.vendas4.service;

import com.br.vendas4.domain.entity.Pedido;
import com.br.vendas4.domain.enums.StatusPedido;
import com.br.vendas4.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {

    Pedido salvar(PedidoDTO pedidoDTO);

    Optional<Pedido> obterPedidoCompleto(Integer id);

    void atualizaStatus(Integer id, StatusPedido statusPedido);
}

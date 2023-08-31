package com.br.vendas4.service;

import com.br.vendas4.domain.entity.Pedido;
import com.br.vendas4.rest.dto.PedidoDTO;

public interface PedidoService {

    Pedido salvar(PedidoDTO pedidoDTO);
}

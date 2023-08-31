package com.br.vendas4.rest.controller;

import com.br.vendas4.domain.entity.Pedido;
import com.br.vendas4.domain.repository.Clientes;
import com.br.vendas4.domain.repository.Pedidos;
import com.br.vendas4.domain.repository.Produtos;
import com.br.vendas4.rest.dto.PedidoDTO;
import com.br.vendas4.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody PedidoDTO pedidoDTO){
        Pedido pedido = service.salvar(pedidoDTO);

        return pedido.getId();
    }

}

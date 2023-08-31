package com.br.vendas4.service.impl;

import com.br.vendas4.domain.entity.Cliente;
import com.br.vendas4.domain.entity.ItemPedido;
import com.br.vendas4.domain.entity.Pedido;
import com.br.vendas4.domain.entity.Produto;
import com.br.vendas4.domain.repository.Clientes;
import com.br.vendas4.domain.repository.ItensPedido;
import com.br.vendas4.domain.repository.Pedidos;
import com.br.vendas4.domain.repository.Produtos;
import com.br.vendas4.exception.RegraNegocioException;
import com.br.vendas4.rest.dto.ItemPedidoDTO;
import com.br.vendas4.rest.dto.PedidoDTO;
import com.br.vendas4.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private Pedidos repository;

    @Autowired
    private Clientes clientesRepository;

    @Autowired
    private Produtos produtosRepository;

    @Autowired
    private ItensPedido itensPedidoRepository;

    @Override
    @Transactional
    public Pedido salvar(PedidoDTO pedidoDTO) {
        Integer idCliente = pedidoDTO.getCliente();
        Cliente cliente = clientesRepository.findById(idCliente)
                .orElseThrow(() -> new RegraNegocioException("Código de cliente inválido"));

        Pedido pedido = new Pedido();

        pedido.setTotal(pedidoDTO.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);

        List<ItemPedido> itemsPedidos = converterItems(pedido, pedidoDTO.getItens());
        repository.save(pedido);
        itensPedidoRepository.saveAll(itemsPedidos);
        pedido.setItens(itemsPedidos);

        return pedido;
    }

    private List<ItemPedido> converterItems(Pedido pedido,List<ItemPedidoDTO> items){
        if(items.isEmpty()){
            throw new RegraNegocioException("Não é possível realizar um pedido sem itens.");
        }

        return items.stream().map(dto ->{
            Integer idProduto = dto.getProduto();
            Produto produto = produtosRepository.findById(idProduto)
                    .orElseThrow(() -> new RegraNegocioException("Código de produto inválido: "+ idProduto));

            ItemPedido itemPedido = new ItemPedido();

            itemPedido.setQuantidade(dto.getQuantidade());
            itemPedido.setPedido(pedido);
            itemPedido.setProduto(produto);

            return itemPedido;
        }).collect(Collectors.toList());
    }
}

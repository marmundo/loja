package com.marcelodamasceno.services;

import java.util.Collection;

import com.marcelodamasceno.model.Pedido;

public interface PedidoService {

	public abstract void criarPedido(Pedido pedido);

	public abstract void atualizarPedido(Pedido pedido);

	public abstract void deletePedido(Long id);

	public abstract Collection<Pedido> getPedidos();

	public abstract Pedido getPedido(Long id);

}
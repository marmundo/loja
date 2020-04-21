package com.marcelodamasceno.services;

import java.util.Collection;

import com.marcelodamasceno.model.Pedido;

public interface PedidoService {

	public abstract void criarPedido(Pedido pedido);

	public abstract void atualizarPedido(Pedido pedido);

	public abstract void deletePedido(String id);

	public abstract Collection<Pedido> getPedido();
}
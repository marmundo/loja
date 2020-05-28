package com.marcelodamasceno.services;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.marcelodamasceno.model.Pedido;
import com.marcelodamasceno.repository.PedidoRepository;

@Service
public class PedidoServiceImpl implements PedidoService {

	PedidoRepository pedidoRepo;

	public PedidoServiceImpl(PedidoRepository pedidoRepo) {
		super();
		this.pedidoRepo = pedidoRepo;
	}

	@Override
	public void criarPedido(Pedido pedido) {
		pedidoRepo.save(pedido);
	}

	@Override
	public void atualizarPedido(Pedido pedido) {
		pedidoRepo.save(pedido);
	}

	@Override
	public void deletePedido(Long id) {
		pedidoRepo.deleteById(id);
	}

	@Override
	public Collection<Pedido> getPedidos() {
		return pedidoRepo.findAll();
	}

	@Override
	public Pedido getPedido(Long id) {
		return pedidoRepo.findById(id).orElse(new Pedido());
	}

}

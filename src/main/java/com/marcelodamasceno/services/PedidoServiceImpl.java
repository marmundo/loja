package com.marcelodamasceno.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.marcelodamasceno.model.Cliente;
import com.marcelodamasceno.model.Pedido;
import com.marcelodamasceno.model.Produto;

@Service
public class PedidoServiceImpl implements PedidoService {

	private static Map<String, Pedido> pedidoRepo = new HashMap<>();
	private static Map<String, Cliente> clienteRepo = new HashMap<>();
	static {
		Produto produto = new Produto((long) 1, "acucar", 1);
		List<Produto> produtos = new ArrayList<Produto>();
		produtos.add(produto);
		

		Date data = new Date();
		double valorTotal = 0;
		for (Produto produto2 : produtos) {
			valorTotal += produto2.getValor();
		}

		ClienteServiceImpl clienteServico=new ClienteServiceImpl();
		Cliente marceloDamasceno = clienteServico.getCliente((long) 1);
		
		Pedido pedido = new Pedido((long) 1, produtos, marceloDamasceno, data, valorTotal);
		List<Pedido> pedidos=new ArrayList<Pedido>();
		pedidos.add(pedido);
//		marceloDamasceno.setPedidos(pedidos);
		
		pedidoRepo.put(pedido.getId().toString(), pedido);
//		clienteRepo.put(marceloDamasceno.getId().toString(), marceloDamasceno);
	}

	@Override
	public void criarPedido(Pedido pedido) {
		pedidoRepo.put(pedido.getId().toString(), pedido);
	}

	@Override
	public void atualizarPedido(Pedido pedido) {
		pedidoRepo.remove(pedido.getId().toString());
		pedidoRepo.put(pedido.getId().toString(), pedido);
	}

	@Override
	public void deletePedido(String id) {
		pedidoRepo.remove(id);
	}

	@Override
	public Collection<Pedido> getPedidos() {
		return pedidoRepo.values();
	}

	@Override
	public Pedido getPedido(Long id) {
		return pedidoRepo.get(id.toString());
	}

}

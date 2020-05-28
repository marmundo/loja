package com.marcelodamasceno.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.marcelodamasceno.model.Pedido;
import com.marcelodamasceno.services.PedidoService;
import com.marcelodamasceno.services.PedidoServiceImpl;

@Controller
public class PedidoController implements CrudController {

	private final PedidoService servico;

	@Autowired
	public PedidoController(PedidoServiceImpl servico) {
		this.servico = servico;
	}

	@Override
	public void cria(Object object) {
		servico.criarPedido((Pedido) object);
	}

	@Override
	public void remove(Long id) {
		servico.deletePedido(id.toString());
	}

	@Override
	public void atualiza(Object object) {
		servico.atualizarPedido((Pedido) object);
	}
	
	@GetMapping("/pedidos")
	public String lista(Model model) {
		model.addAttribute("clientes",servico.getPedidos());
		return "clientes";
	}

}

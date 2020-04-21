package com.marcelodamasceno.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.marcelodamasceno.model.Cliente;
import com.marcelodamasceno.services.ClienteService;
import com.marcelodamasceno.services.ClienteServiceImpl;

@Controller
public class ClienteController implements CrudController {

	private final ClienteService servico;

	@Autowired
	public ClienteController(ClienteServiceImpl servico) {
		this.servico = servico;
	}

	@Override
	public void cria(Object cliente) {
		servico.criarCliente((Cliente) cliente);

	}

	@Override
	public void remove(Long id) {
		servico.deleteCliente(id.toString());

	}

	@Override
	public void atualiza(Object object) {
		servico.atualizarCliente((Cliente) object);

	}

	@GetMapping("/clientes")
	public String lista(Model model) {
		model.addAttribute("clientes",servico.getClientes());
		return "clientes";
	}
	
//	@GetMapping("/cliente")
//	public String listaCliente(Model model) {
//		List<Cliente> list = new ArrayList<Cliente>(servico.getClientes());
//	
//		model.addAttribute("cliente",list.get(0));
//		return "cliente";
//	}

	
	@GetMapping("/teste")
	public String getTeste() {
		return "teste";
	}
}

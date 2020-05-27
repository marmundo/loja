package com.marcelodamasceno.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.marcelodamasceno.model.Cliente;
import com.marcelodamasceno.services.ClienteService;
import com.marcelodamasceno.services.ClienteServiceImpl;

@Controller
@RequestMapping("/clientes")
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

	@GetMapping("/")
	public String lista(Model model) {
		
		List<Cliente> lista=new ArrayList<>(servico.getClientes());
		Collections.sort(lista);
//		System.out.println("Arrumada: "+lista);
		model.addAttribute("clientes", lista);
		return "clientes/list";
	}

	@GetMapping("/remover/{id}")
	public String remover(@PathVariable Long id) {
		remove(id);
		return "clientes/view";
	}

	@GetMapping("{id}")
	public String view(@PathVariable("id") Long id, Model model) {
		Cliente cliente = servico.getCliente(id);
		model.addAttribute("cliente", cliente);
		return "clientes/view";
	}

	@GetMapping("/alterar/{id}")
	public String alterar(@PathVariable("id") Long id, Model model) {
		Cliente cliente = servico.getCliente(id);
		model.addAttribute("cliente", cliente);
		return "clientes/form";
	}

	@PostMapping("")
	public String create(Cliente cliente) {
		if (cliente.getId() == null) {
			Long id = (long) (int) (Math.random() * 50 + 1);
			cliente.setId(id);
		}
		servico.criarCliente(cliente);
		return "redirect:/clientes/";
	}

	@GetMapping("/novo")
	public String createForm(Cliente cliente) {
		return "clientes/form";
	}

}

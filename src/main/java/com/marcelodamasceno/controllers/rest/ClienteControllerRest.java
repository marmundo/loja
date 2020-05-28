package com.marcelodamasceno.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import com.marcelodamasceno.controllers.CrudController;
import com.marcelodamasceno.model.Cliente;
import com.marcelodamasceno.services.ClienteService;
import com.marcelodamasceno.services.ClienteServiceImpl;

//@Controller
public class ClienteControllerRest implements CrudController {

	private final ClienteService servico;

	@Autowired
	public ClienteControllerRest(ClienteServiceImpl servico) {
		this.servico=servico;
	}

	@Override
	public void cria(Object cliente) {
		servico.criarCliente((Cliente) cliente);

	}

	@Override
	public void remove(Long id) {
		servico.deleteCliente(id);

	}

	@Override
	public void atualiza(Object object) {
		servico.atualizarCliente((Cliente) object);

	}

	@RequestMapping("/clientes")
	public ResponseEntity<Object> lista() {
		return new ResponseEntity<>(servico.getClientes(),HttpStatus.OK);
	}

}

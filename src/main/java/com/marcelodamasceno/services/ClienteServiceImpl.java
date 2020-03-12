package com.marcelodamasceno.services;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.marcelodamasceno.model.Cliente;

@Service
public class ClienteServiceImpl implements ClienteService {

	private static Map<String, Cliente> clienteRepo = new HashMap<>();
	static {
	 Cliente marceloDamasceno= new Cliente((long) 1,"Marcelo","Damasceno");
     clienteRepo.put(marceloDamasceno.getId().toString(), marceloDamasceno);

  }
	
	@Override
	public void criarCliente(Cliente cliente) {
		clienteRepo.put(cliente.getId().toString(), cliente);

	}

	@Override
	public void atualizarCliente(Cliente cliente) {
		clienteRepo.remove(cliente.getId().toString());
		clienteRepo.put(cliente.getId().toString(), cliente);

	}

	@Override
	public void deleteCliente(String id) {
		clienteRepo.remove(id);

	}

	@Override
	public Collection<Cliente> getClientes() {
		return clienteRepo.values();
	}

}

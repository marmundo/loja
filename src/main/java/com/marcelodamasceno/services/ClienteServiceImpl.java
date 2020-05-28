package com.marcelodamasceno.services;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.marcelodamasceno.model.Cliente;
import com.marcelodamasceno.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {

	private final ClienteRepository clienteRepo;

	public ClienteServiceImpl(ClienteRepository clienteRepo) {
		super();
		this.clienteRepo = clienteRepo;
	}

	@Override
	public void criarCliente(Cliente cliente) {
		clienteRepo.save(cliente);
	}

	@Override
	public void atualizarCliente(Cliente cliente) {
		clienteRepo.save(cliente);
	}

	@Override
	public void deleteCliente(Long id) {
		clienteRepo.deleteById(id);

	}

	@Override
	public Collection<Cliente> getClientes() {
		return clienteRepo.findAll();
	}

	@Override
	public Cliente getCliente(Long id) {
		return clienteRepo.findById(id).orElse(new Cliente());
	}
}
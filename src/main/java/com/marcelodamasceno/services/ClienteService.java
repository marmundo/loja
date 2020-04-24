package com.marcelodamasceno.services;

import java.util.Collection;

import com.marcelodamasceno.model.Cliente;

public interface ClienteService {

	public abstract void criarCliente(Cliente cliente);

	public abstract void atualizarCliente(Cliente cliente);

	public abstract void deleteCliente(String id);

	public abstract Collection<Cliente> getClientes();

	public abstract Cliente getCliente(Long id);
}

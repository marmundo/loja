package com.marcelodamasceno.carga;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.marcelodamasceno.model.Cliente;
import com.marcelodamasceno.model.Pedido;
import com.marcelodamasceno.model.Produto;
import com.marcelodamasceno.repository.ClienteRepository;

@Component
public class RepositoryTest implements ApplicationRunner {

	private static final long ID_CLIENTE_FERNANDO = 11l;
	private static final long ID_CLIENTE_ZE_PEQUENO = 22l;

	private static final long ID_Produto1 = 100l;
	private static final long ID_Produto2 = 101l;
	private static final long ID_Produto3 = 102l;

	private static final long ID_PEDIDO1 = 1000l;
	private static final long ID_PEDIDO2 = 1001l;
	private static final long ID_PEDIDO3 = 1002l;

	@Autowired
	private ClienteRepository clienteRepository;

//    @Override
	public void run(ApplicationArguments applicationArguments) throws Exception {

		System.out.println(">>> Iniciando carga de dados...");
		Cliente fernando = new Cliente(ID_CLIENTE_FERNANDO, "Fernando Boaglio", "Sampa");
		Cliente zePequeno = new Cliente(ID_CLIENTE_ZE_PEQUENO, "Zé Pequeno", "Cidade de Deus");

		Produto dog1 = new Produto(ID_Produto1, "Green Dog tradicional", 25d);
		Produto dog2 = new Produto(ID_Produto2, "Green Dog tradicional picante", 27d);
		Produto dog3 = new Produto(ID_Produto3, "Green Dog max salada", 30d);

		List<Produto> listaPedidoFernando1 = new ArrayList<Produto>();
		listaPedidoFernando1.add(dog1);

		List<Produto> listaPedidoZePequeno1 = new ArrayList<Produto>();
		listaPedidoZePequeno1.add(dog2);
		listaPedidoZePequeno1.add(dog3);

		Pedido pedidoDoFernando = new Pedido(ID_PEDIDO1, fernando, listaPedidoFernando1, dog1.getValor());
		fernando.novoPedido(pedidoDoFernando);

		Pedido pedidoDoZepequeno = new Pedido(ID_PEDIDO2, zePequeno, listaPedidoZePequeno1,
				dog2.getValor() + dog3.getValor());
		zePequeno.novoPedido(pedidoDoZepequeno);

		System.out.println(">>> Pedido 1 - Fernando : " + pedidoDoFernando);
		System.out.println(">>> Pedido 2 - Ze Pequeno: " + pedidoDoZepequeno);

		clienteRepository.saveAndFlush(zePequeno);
		System.out.println(">>> Gravado cliente 2: " + zePequeno);

		List<Produto> listaPedidoFernando2 = new ArrayList<Produto>();
		listaPedidoFernando2.add(dog2);
		Pedido pedido2DoFernando = new Pedido(ID_PEDIDO3, fernando, listaPedidoFernando2, dog2.getValor());
		fernando.novoPedido(pedido2DoFernando);
		clienteRepository.saveAndFlush(fernando);
		System.out.println(">>> Pedido 2 - Fernando : " + pedido2DoFernando);
		System.out.println(">>> Gravado cliente 1: " + fernando);

	}

}
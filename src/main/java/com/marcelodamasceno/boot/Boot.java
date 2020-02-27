package com.marcelodamasceno.boot;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.marcelodamasceno.domain.Cliente;
import com.marcelodamasceno.domain.Pedido;
import com.marcelodamasceno.domain.Produto;
import com.marcelodamasceno.repository.ClienteRepository;
import com.marcelodamasceno.repository.PedidoRepository;
import com.marcelodamasceno.repository.ProdutoRepository;

@Component
public class Boot implements CommandLineRunner {
	
	private final ClienteRepository clienteRepository;
	private final PedidoRepository pedidoRepository;
	private final ProdutoRepository produtoRepository;

	
	public Boot(ClienteRepository clienteRepository, PedidoRepository pedidoRepository,
			ProdutoRepository produtoRepository) {
		super();
		this.clienteRepository = clienteRepository;
		this.pedidoRepository = pedidoRepository;
		this.produtoRepository = produtoRepository;
	}


	@Override
	public void run(String... args) throws Exception {
		Cliente marcelo=new Cliente("Marcelo","Damasceno");
		clienteRepository.save(marcelo);
		
		Produto cafe=new Produto("Café",5.50);
		produtoRepository.save(cafe);
	
		Produto acucar=new Produto("Açucar",5.00);
		produtoRepository.save(acucar);
		
		Pedido pedido=new Pedido();
		ArrayList<Produto> listaDeProdutos=new ArrayList<>();
		listaDeProdutos.add(cafe);
		listaDeProdutos.add(acucar);
		pedido.setProdutos(listaDeProdutos);
		pedido.setCliente(marcelo);
		pedido.setData(new Date());
		pedido.setValorTotal(cafe.getValor()+acucar.getValor());
		pedidoRepository.save(pedido);
		
		
	}

}

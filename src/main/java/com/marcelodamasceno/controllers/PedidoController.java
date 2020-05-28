package com.marcelodamasceno.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.marcelodamasceno.model.Cliente;
import com.marcelodamasceno.model.Pedido;
import com.marcelodamasceno.model.Produto;
import com.marcelodamasceno.services.ClienteService;
import com.marcelodamasceno.services.ClienteServiceImpl;
import com.marcelodamasceno.services.PedidoService;
import com.marcelodamasceno.services.PedidoServiceImpl;
import com.marcelodamasceno.services.ProdutoService;
import com.marcelodamasceno.services.ProdutoServiceImpl;

@Controller
@RequestMapping("/pedidos")
public class PedidoController implements CrudController {

	private final PedidoService pedidoServico;
	private final ProdutoService produtoService;
	private final ClienteService clienteServico;

	public PedidoController(PedidoServiceImpl pedidoServico, ProdutoServiceImpl produtoService,
			ClienteServiceImpl clienteServico) {
		this.pedidoServico = pedidoServico;
		this.produtoService = produtoService;
		this.clienteServico = clienteServico;
	}

	@Override
	public void cria(Object object) {
		pedidoServico.criarPedido((Pedido) object);
	}

	@Override
	public void remove(Long id) {
		Pedido pedidoParaRemover = pedidoServico.getPedido(id);
		Cliente c = clienteServico.getCliente(pedidoParaRemover.getCliente().getId());
		c.getPedidos().remove(pedidoParaRemover);
		clienteServico.atualizarCliente(c);
		pedidoServico.deletePedido(id.toString());
	}

	@Override
	public void atualiza(Object object) {
		pedidoServico.atualizarPedido((Pedido) object);
	}

	@GetMapping("/")
	public String lista(Model model) {
		System.out.println("OK");
		List<Pedido> lista = new ArrayList<>(pedidoServico.getPedidos());
		
		model.addAttribute("pedidos", lista);
		return "pedidos/list";
	}

	@GetMapping("/remover/{id}")
	public String remover(@PathVariable Long id) {
		remove(id);
		return "pedidos/view";
	}

	@GetMapping("{id}")
	public String view(@PathVariable("id") Long id, Model model) {
		Pedido pedido = pedidoServico.getPedido(id);
		model.addAttribute("pedido", pedido);
		return "pedidos/view";
	}

	@GetMapping("/alterar/{id}")
	public String alterar(@PathVariable("id") Long id, Model model) {
		Pedido pedido = pedidoServico.getPedido(id);
		model.addAttribute("produtos", produtoService.getProdutos());
		model.addAttribute("clientes", clienteServico.getClientes());
		model.addAttribute("pedido", pedido);

		return "pedidos/form";
	}

	@PostMapping("")
	public String create(Pedido pedido, BindingResult result, Model model) {
//		if (result.hasErrors()) {
//			model.addAttribute("formErrors",result.getAllErrors());
//			return "pedidos/form";
//		}
		System.out.println("PEDIDO---" + pedido);
		if (pedido.getId() != null) {

			Pedido pedidoParaAlterar = pedidoServico.getPedido(pedido.getId());
			Cliente c = clienteServico.getCliente(pedidoParaAlterar.getCliente().getId());
			pedidoParaAlterar.setProdutos(pedido.getProdutos());
			double valorTotal = 0;
			for (Produto i : pedido.getProdutos()) {
				valorTotal += i.getValor();
			}
			pedidoParaAlterar.setData(pedido.getData());
			pedidoParaAlterar.setValorTotal(valorTotal);
			System.out.println(c.getPedidos());
			if (c.getPedidos() != null)
				c.getPedidos().remove(pedidoParaAlterar);
			c.getPedidos().add(pedidoParaAlterar);
			clienteServico.atualizarCliente(c);
		} else {
			Cliente c = clienteServico.getCliente(pedido.getCliente().getId());
			double valorTotal = 0;
			for (Produto i : pedido.getProdutos()) {
				valorTotal += i.getValor();
			}
			pedido.setValorTotal(valorTotal);
			pedidoServico.criarPedido(pedido);
			c.getPedidos().add(pedido);
			clienteServico.atualizarCliente(c);
		}
		if (pedido.getId() == null) {
			Long id = (long) (int) (Math.random() * 50 + 1);
			pedido.setId(id);
		}
		pedidoServico.criarPedido(pedido);
		return "redirect:/pedidos/";
	}

	@GetMapping("/novo")
	public String createForm(Pedido pedido) {
		return "pedidos/form";
	}

}

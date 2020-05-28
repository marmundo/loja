package com.marcelodamasceno.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
	private final ClienteService clienteServico;
	private final ProdutoService produtoServico;

	@Autowired
	public PedidoController(PedidoServiceImpl pedidoServico, ClienteServiceImpl clienteServico,
			ProdutoServiceImpl produtoServico) {
		this.clienteServico = clienteServico;
		this.produtoServico = produtoServico;
		this.pedidoServico = pedidoServico;
	}

	@Override
	public void cria(Object object) {
		pedidoServico.criarPedido((Pedido) object);
	}

	@Override
	public void remove(Long id) {
		pedidoServico.deletePedido(id.toString());
	}

	@Override
	public void atualiza(Object object) {
		pedidoServico.atualizarPedido((Pedido) object);
	}

	@GetMapping("/")
	public String lista(Model model) {

		List<Pedido> lista = new ArrayList<>(pedidoServico.getPedidos());
		model.addAttribute("pedidos", lista);
		return "pedidos/list";
	}

	@GetMapping("/remover/{id}")
	public String remover(@PathVariable Long id) {
		remove(id);
		return "clientes/view";
	}

	@GetMapping("{id}")
	public String view(@PathVariable("id") Long id, Model model) {
		Pedido pedido = pedidoServico.getPedido(id);
		System.out.println(pedido);
		model.addAttribute("pedido", pedido);
		return "pedidos/view";
	}

	@GetMapping("/alterar/{id}")
	public String alterar(@PathVariable("id") Long id, Model model) {
		Pedido pedido = pedidoServico.getPedido(id);
		model.addAttribute("pedido", pedido);
		model.addAttribute("clientes", clienteServico.getClientes());
		model.addAttribute("produtos", produtoServico.getProdutos());
		return "pedidos/form";
	}

	@PostMapping("")
	public String create(@Valid Pedido pedido, BindingResult result, Model model) {

		if (result.hasErrors()) {
			model.addAttribute("formErrors", result.getAllErrors());
			return "redirect:/pedidos/form";
		}

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

			c.getPedidos().remove(pedidoParaAlterar.getId().intValue());
			c.getPedidos().add(pedidoParaAlterar);
			clienteServico.atualizarCliente(c);
		} else {
			Cliente c = clienteServico.getCliente(pedido.getCliente().getId());
			double valorTotal = 0;
			for (Produto i : pedido.getProdutos()) {
				valorTotal += i.getValor();
			}
			pedido.setValorTotal(valorTotal);
			pedidoServico.atualizarPedido(pedido);
			c.getPedidos().add(pedido);
			clienteServico.atualizarCliente(c);
		}
		model.addAttribute("globalMessage", "Pedido gravado com sucesso");
		return "redirect:/pedidos/" + pedido.getId();
	}

	@GetMapping("/novo")
	public String createForm(Pedido pedido, Model model) {
		model.addAttribute("clientes", clienteServico.getClientes());
		model.addAttribute("produtos", produtoServico.getProdutos());
		return "pedidos/form";
	}

}

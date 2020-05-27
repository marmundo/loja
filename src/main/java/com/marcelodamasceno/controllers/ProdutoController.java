package com.marcelodamasceno.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.marcelodamasceno.model.Produto;
import com.marcelodamasceno.services.ProdutoService;
import com.marcelodamasceno.services.ProdutoServiceImpl;

@Controller
@RequestMapping("/produtos")
public class ProdutoController implements CrudController {

	private final ProdutoService servico;

	@Autowired
	public ProdutoController(ProdutoServiceImpl servico) {
		this.servico = servico;
	}

	@Override
	public void cria(Object object) {
		servico.criarProduto((Produto) object);
	}

	@Override
	public void remove(Long id) {
		servico.deleteProduto(id.toString());
	}

	@Override
	public void atualiza(Object object) {
		servico.atualizarProduto((Produto) object);
	}
	
	@GetMapping("/")
	public String lista(Model model) {
		List<Produto> lista=new ArrayList<>(servico.getProdutos());
		model.addAttribute("produtos", lista);
		return "produtos/list";
	}

	@GetMapping("/remover/{id}")
	public String remover(@PathVariable Long id) {
		remove(id);
		return "produtos/view";
	}

	@GetMapping("{id}")
	public String view(@PathVariable("id") Long id, Model model) {
		Produto produto = servico.getProduto(id);
		model.addAttribute("produto", produto);
		return "produtos/view";
	}

	@GetMapping("/alterar/{id}")
	public String alterar(@PathVariable("id") Long id, Model model) {
		Produto produto = servico.getProduto(id);
		model.addAttribute("cliente", produto);
		return "produtos/form";
	}

	@PostMapping("")
	public String create(Produto produto) {
		if (produto.getId() == null) {
			Long id = (long) (int) (Math.random() * 50 + 1);
			produto.setId(id);
		}
		servico.criarProduto(produto);
		return "redirect:/produtos/";
	}

	@GetMapping("/novo")
	public String createForm(Produto produto) {
		return "produtos/form";
	}

}

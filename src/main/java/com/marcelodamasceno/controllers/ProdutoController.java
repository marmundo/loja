package com.marcelodamasceno.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.marcelodamasceno.model.Produto;
import com.marcelodamasceno.services.ProdutoService;
import com.marcelodamasceno.services.ProdutoServiceImpl;

@Controller
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
	
	@GetMapping("/produtos")
	public String lista(Model model) {
		model.addAttribute("clientes",servico.getProdutos());
		return "clientes";
	}

}

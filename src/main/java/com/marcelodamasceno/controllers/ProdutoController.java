package com.marcelodamasceno.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import com.marcelodamasceno.model.Produto;
import com.marcelodamasceno.services.ProdutoService;
import com.marcelodamasceno.services.ProdutoServiceImpl;

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

}

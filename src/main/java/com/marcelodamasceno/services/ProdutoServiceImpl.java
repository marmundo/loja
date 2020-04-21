package com.marcelodamasceno.services;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.marcelodamasceno.model.Produto;

public class ProdutoServiceImpl implements ProdutoService {

	private static Map<String, Produto> produtoRepo = new HashMap<>();
	static {
		Produto acucar = new Produto((long) 1, "acucar", 2.50);
		produtoRepo.put(acucar.getId().toString(), acucar);

	}

	@Override
	public void criarProduto(Produto produto) {
		produtoRepo.put(produto.getId().toString(), produto);
	}

	@Override
	public void atualizarProduto(Produto produto) {
		produtoRepo.remove(produto.getId().toString());
		produtoRepo.put(produto.getId().toString(), produto);

	}

	@Override
	public void deleteProduto(String id) {
		produtoRepo.remove(id);
	}

	@Override
	public Collection<Produto> getProduto() {
		return produtoRepo.values();
	}

}

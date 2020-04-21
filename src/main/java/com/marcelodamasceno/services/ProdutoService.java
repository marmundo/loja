package com.marcelodamasceno.services;

import java.util.Collection;

import com.marcelodamasceno.model.Produto;

public interface ProdutoService {

	public abstract void criarProduto(Produto produto);

	public abstract void atualizarProduto(Produto produto);

	public abstract void deleteProduto(String id);

	public abstract Collection<Produto> getProdutos();
}
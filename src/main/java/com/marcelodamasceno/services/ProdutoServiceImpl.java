package com.marcelodamasceno.services;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.marcelodamasceno.model.Produto;
import com.marcelodamasceno.repository.ProdutoRepository;

@Service
public class ProdutoServiceImpl implements ProdutoService {

	ProdutoRepository produtoRepo;

	public ProdutoServiceImpl(ProdutoRepository produtoRepo) {
		super();
		this.produtoRepo = produtoRepo;
	}

	@Override
	public void criarProduto(Produto produto) {
		produtoRepo.save(produto);
	}

	@Override
	public void atualizarProduto(Produto produto) {
		produtoRepo.save(produto);
	}

	@Override
	public void deleteProduto(Long id) {
		produtoRepo.deleteById(id);
	}

	@Override
	public Collection<Produto> getProdutos() {
		return produtoRepo.findAll();
	}

	@Override
	public Produto getProduto(Long id) {
		return produtoRepo.findById(id).orElse(new Produto());
	}

}

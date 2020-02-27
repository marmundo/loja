package com.marcelodamasceno.repository;

import org.springframework.data.repository.CrudRepository;

import com.marcelodamasceno.domain.Produto;

public interface ProdutoRepository extends CrudRepository<Produto,Long> {


}

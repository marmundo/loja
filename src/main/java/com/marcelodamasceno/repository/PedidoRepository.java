package com.marcelodamasceno.repository;

import org.springframework.data.repository.CrudRepository;

import com.marcelodamasceno.domain.Pedido;

public interface PedidoRepository extends CrudRepository<Pedido,Long> {

}

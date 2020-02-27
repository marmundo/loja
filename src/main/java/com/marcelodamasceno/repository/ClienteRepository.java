package com.marcelodamasceno.repository;
import org.springframework.data.repository.CrudRepository;

import com.marcelodamasceno.domain.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente,Long> {

}

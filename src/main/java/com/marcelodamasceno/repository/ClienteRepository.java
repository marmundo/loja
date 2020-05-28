package com.marcelodamasceno.repository;

import com.marcelodamasceno.model.Cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {
	Cliente findByPrimeiroNome(String primeiroNome);

	Page<Cliente> findByPrimeiroNomeContainingAllIgnoringCase(@Param("primeiroNome") String primeiroNome,Pageable pageable);

	Cliente findByPrimeiroNomeAllIgnoringCase(@Param("primeiroNome") String primeiroNome);
}

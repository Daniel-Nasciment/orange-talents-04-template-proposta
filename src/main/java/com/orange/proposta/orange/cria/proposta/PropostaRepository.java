package com.orange.proposta.orange.cria.proposta;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.orange.proposta.orange.consulta.dados.StatusAnalisado;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, Long> {

	
	Optional<String> findByDocumento(String documento);
	
	@Query("select p from Proposta p where p.status = :status and p.cartao is null")
	Set<Proposta> findByStatusWhereCartaoIsNull(StatusAnalisado status);

}

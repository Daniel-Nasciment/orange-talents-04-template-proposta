package com.orange.proposta.orange.carteira;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orange.proposta.orange.cartoes.Cartao;

@Repository
public interface CarteiraRepository extends JpaRepository<Carteira, Long> {

	boolean existsByTipoCarteiraAndCartao(TipoCarteira tipoCarteira, Cartao cartao);

}

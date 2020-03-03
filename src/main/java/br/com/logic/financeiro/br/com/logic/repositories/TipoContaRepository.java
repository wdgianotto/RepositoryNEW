package br.com.logic.financeiro.br.com.logic.repositories;

import br.com.logic.financeiro.br.com.logic.domain.TipoConta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoContaRepository extends JpaRepository<TipoConta, Integer> {
}

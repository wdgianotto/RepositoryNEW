package br.com.logic.financeiro.br.com.logic.repositories;

import br.com.logic.financeiro.br.com.logic.domain.Banco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BancoRepository extends JpaRepository<Banco, Integer> {
}

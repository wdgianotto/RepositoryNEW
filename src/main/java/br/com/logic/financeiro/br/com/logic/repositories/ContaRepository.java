package br.com.logic.financeiro.br.com.logic.repositories;

import br.com.logic.financeiro.br.com.logic.domain.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer> {

    @Query("select c " +
            "from Conta c " +
            "where c.numeroConta = :numeroConta and c.banco.id = :banco and c.tipoConta.id = :tipoConta")
    List<Conta> findAllByNumeroContaAndIdBancoAndIdTipoConta(
            @Param("numeroConta") Integer numeroConta,
            @Param("banco") Integer banco,
            @Param("tipoConta") Integer tipoConta
    );

    @Modifying
    @Query("update Conta " +
            "set saldo = :saldo, credito = :credito " +
            "where id = :id")
    Integer updateSaldoCreditoSaque(
            @Param("id") Integer id,
            @Param("saldo") Double saldo,
            @Param("credito") Double credito

    );

    @Modifying
    @Query("update Conta " +
            "set saldo = :saldo " +
            "where id = :id")
    void updateSaldoDeposito(
            @Param("id") Integer id,
            @Param("saldo") Double saldo
    );

    Optional <List<Conta>> findByNumeroConta(Integer numeroConta);

}


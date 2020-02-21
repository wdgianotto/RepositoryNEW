package br.com.logic.financeiro.br.com.logic.repositories;

import br.com.logic.financeiro.br.com.logic.vo.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
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

    Optional <List<Conta>> findByNumeroConta(Integer numeroConta);


//    @Query("select c " +
//            "from Conta c " +
//            "where c.numeroConta = :numeroConta and c.idtipoconta = :idtipoconta and c.idbanco = :idbanco")
//    List<Conta> findAllByNumeroContaAndIdTipoContaAndIdBanco(
//            @Param("numeroConta") Integer numeroConta,
//            @Param("idtipoconta") Integer idTipoConta,
//            @Param("idbanco") Integer idBanco
//    );
}


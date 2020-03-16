package br.com.logic.financeiro.br.com.logic.services;

import br.com.logic.financeiro.br.com.logic.domain.Banco;
import br.com.logic.financeiro.br.com.logic.domain.Conta;
import br.com.logic.financeiro.br.com.logic.domain.TipoConta;
import br.com.logic.financeiro.br.com.logic.repositories.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CriarContaService {

    @Autowired
    private ContaRepository contaRepository;

    public List<Conta> validarCriacaoConta(Banco banco, TipoConta tipoConta, Integer numeroConta){
        List<Conta> listaContaCliente;

        listaContaCliente = contaRepository.findAllByNumeroContaAndIdBancoAndIdTipoConta(numeroConta,
                tipoConta.getId(), banco.getId());
        return listaContaCliente;
    }


}

package br.com.logic.financeiro.br.com.logic.services;

import br.com.logic.financeiro.br.com.logic.repositories.ContaRepository;
import br.com.logic.financeiro.br.com.logic.domain.Conta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Deposito {

    @Autowired
    private ContaRepository contaRepository;

    public void alterarDadosDeposito(Conta conta, Double valor){
        conta.setSaldo(conta.getSaldo() + valor);
    }

    public void salvarDadosDeposito(Integer id, Double saldo){
        contaRepository.updateSaldoDeposito(id, saldo);
        System.out.println("Transação realizada com sucesso!");
    }
}

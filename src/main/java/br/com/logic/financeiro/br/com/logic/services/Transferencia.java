package br.com.logic.financeiro.br.com.logic.services;

import br.com.logic.financeiro.br.com.logic.domain.Conta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Transferencia {

    @Autowired
    private Saque saque;

    @Autowired
    private Deposito deposito;

    public Boolean validarTransferencia(Conta conta, Conta contaBeneficiario, Double valorTransferencia){
        Boolean validarConta = saque.validarSaque(conta, valorTransferencia);

        Boolean validarTransferencia;

        if(validarConta && !conta.equals(contaBeneficiario)){
            validarTransferencia = true;
        }else{
            validarTransferencia = false;
        }
        return validarTransferencia;
    }

    public void alterarDadosTransferencia(Conta conta, Conta contaBeneficiario, Double valorTransferencia){
        saque.alterarDadosSaque(conta, valorTransferencia);
        deposito.alterarDadosDeposito(contaBeneficiario,valorTransferencia);
    }

    public void salvarDadosTransferencia(Conta conta, Conta contaBeneficiario){
        saque.salvarDadosSaque(conta.getId(), conta.getSaldo(), conta.getCredito());
        deposito.salvarDadosDeposito(contaBeneficiario.getId(), contaBeneficiario.getSaldo());
    }
}

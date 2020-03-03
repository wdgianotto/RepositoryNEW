package br.com.logic.financeiro.br.com.logic.services;

import br.com.logic.financeiro.br.com.logic.repositories.ContaRepository;
import br.com.logic.financeiro.br.com.logic.domain.Conta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class Saque {

    @Autowired
    private ContaRepository contaRepository;

    Scanner teclado = new Scanner(System.in);

    public boolean validarSaque(Conta conta, Double valorSaque){

        Boolean validarSaque;

        if(conta.getSaldo() + conta.getCredito() >= valorSaque){
            validarSaque = true;
        }else{
            validarSaque = false;
        }
        return validarSaque;
    }

    public void alterarDadosSaque(Conta conta, Double valorSaque){
        if(conta.getSaldo() >= valorSaque){
            conta.setSaldo(conta.getSaldo() - valorSaque - conta.getBanco().getTaxa());
        }else if(conta.getSaldo() > 0){
            Double aux = conta.getSaldo() - valorSaque;
            conta.setSaldo(aux - conta.getBanco().getTaxa());
            conta.setCredito(conta.getCredito() + aux);
        }else{
            conta.setCredito(conta.getCredito() - valorSaque);
            conta.setSaldo(conta.getSaldo() - valorSaque - conta.getBanco().getTaxa());
        }
    }

    public void salvarDadosSaque(Integer id, Double saldo, Double credito){
        try {
            Integer validaSaque = contaRepository.updateSaldoCreditoSaque(id, saldo, credito);

        }catch(Exception e){
            System.out.println(e);
        }
    }
}

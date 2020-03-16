package br.com.logic.financeiro.br.com.logic.services;

import br.com.logic.financeiro.br.com.logic.domain.Cliente;
import br.com.logic.financeiro.br.com.logic.domain.Conta;
import br.com.logic.financeiro.br.com.logic.domain.DTO.ContaDTO;
import br.com.logic.financeiro.br.com.logic.domain.DTO.ContaDTOCriarConta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContaService {

    @Autowired
    private ClienteService clienteService;

    public Conta converteDTOConta(ContaDTO contaDTO){
        Conta conta = null;
        try{
            conta = new Conta(contaDTO.getId(), contaDTO.getNumeroConta(), contaDTO.getSaldo(),
                    clienteService.obterClienteById(contaDTO.getCliente()), clienteService.obterBanco(contaDTO.getBanco()),
                    clienteService.obterTipoConta(contaDTO.getTipoConta()), contaDTO.getCredito());
        }catch(Exception e){
            System.out.println("Não foi possível converter os dados da conta");
        }
        return conta;
    }

    public Conta converteDTOContaCriarConta(ContaDTOCriarConta contaDTO){
        Conta conta = null;
        Cliente cliente = clienteService.obterCliente(contaDTO.getCpf());
        try{

            if(cliente == null){
                conta = new Conta(contaDTO.getId(), contaDTO.getNumeroConta(), contaDTO.getSaldo(),
                        new Cliente(null, contaDTO.getNome(), contaDTO.getCpf()),
                        clienteService.obterBanco(contaDTO.getBanco()),
                        clienteService.obterTipoConta(contaDTO.getTipoConta()),
                        contaDTO.getCredito());
            }else {
                conta = new Conta(contaDTO.getId(), contaDTO.getNumeroConta(), contaDTO.getSaldo(),
                        cliente,
                        clienteService.obterBanco(contaDTO.getBanco()),
                        clienteService.obterTipoConta(contaDTO.getTipoConta()), contaDTO.getCredito());
            }
        }catch(Exception e){
            System.out.println("Não foi possível converter os dados da conta");
        }
        return conta;
    }
}

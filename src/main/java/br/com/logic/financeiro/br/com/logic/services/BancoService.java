package br.com.logic.financeiro.br.com.logic.services;

import br.com.logic.financeiro.br.com.logic.domain.Conta;
import br.com.logic.financeiro.br.com.logic.domain.DTO.ContaDTO;
import br.com.logic.financeiro.br.com.logic.repositories.BancoRepository;
import br.com.logic.financeiro.br.com.logic.repositories.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Scanner;

@Service
public class BancoService {

    Scanner teclado = new Scanner(System.in);

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private Saque saque;

    @Autowired
    private Deposito deposito;

    @Autowired
    private MenuLogado menuLogado;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private Transferencia transferencia;

    @Autowired
    private MensagemService mensagemService;

    @Transactional
    public void excluirConta(Conta conta) {
        String respostaExclusaoConta = mensagemService.enviarMensagemConsumoString("Deseja excluir a conta? (s/n)");

        if("s".equals(respostaExclusaoConta)){
            contaRepository.deleteById(conta.getId());
            System.out.println("Conta excluída com sucesso!");
        } else{
            menuLogado.menuLogadoCliente(conta);
        }
    }


    public void gerarRelatorioCliente(Conta conta) {
        System.out.println("Nome: " + conta.getCliente().getNome() + ", Banco: " + conta.getBanco().getNome() +
                ", Tipo da conta: " + conta.getTipoConta().getTipoConta() + ", Saldo: " + conta.getSaldo() +
                ", Credito: " + conta.getCredito());
        menuLogado.menuLogadoCliente(conta);
    }


    @Transactional
    public void sacar(Conta conta, Double valorSaque){

        Boolean validaSaqueSaldo = saque.validarSaque(conta, valorSaque);

        if(validaSaqueSaldo){

            saque.alterarDadosSaque(conta, valorSaque);
            saque.salvarDadosSaque(conta.getId(), conta.getSaldo(), conta.getCredito());
            System.out.println("Saque realizado com sucesso!");

        }else{
            System.out.println("Saldo insuficiente, saldo: " + conta.getSaldo() + " Credito: " + conta.getCredito() +
                    " Tipo da conta: " + conta.getTipoConta().getTipoConta() + " Valor solicitado: " + valorSaque);
        }
    }

    @Transactional
    public void depositar(Conta conta, Double valorDeposito){
        deposito.alterarDadosDeposito(conta, valorDeposito);
        deposito.salvarDadosDeposito(conta.getId(), conta.getSaldo());
    }


    @Transactional
    public void transferir(Conta conta, Double valorTransferencia){

        Conta contaBeneficiario = clienteService.coletarDadosSelecionarConta(clienteService.menuEscolheNumeroConta());

        Boolean validarTransferencia = transferencia.validarTransferencia(conta, contaBeneficiario, valorTransferencia);
        if(validarTransferencia){
            transferencia.alterarDadosTransferencia(conta, contaBeneficiario, valorTransferencia);
            transferencia.salvarDadosTransferencia(conta, contaBeneficiario);
        }else{
            if(conta.equals(contaBeneficiario)){
                System.out.println("Não é possível transferir para mesma conta!");
            }
            else{
                System.out.println("Saldo insuficiente, saldo: " + conta.getSaldo() + " Credito: " + conta.getCredito() +
                        " Tipo da conta: " + conta.getTipoConta().getTipoConta() + " Valor solicitado: " + validarTransferencia);
            }
        }
    }

    public Double obterValorSaque(){
        Double valorSaque = mensagemService.enviarMensagemConsumoDouble("Qual o valor que deseja sacar?\n");
        return valorSaque;
    }

    public Double obterValorDeposito(){
        Double valorDeposito = mensagemService.enviarMensagemConsumoDouble("Qual o valor que deseja Depositar?\n");
        return valorDeposito;
    }

    public Double obterValorTransferencia(){
        Double valorTransferencia = mensagemService.enviarMensagemConsumoDouble("Qual o valor que deseja transferir?\n");
        return valorTransferencia;
    }
}
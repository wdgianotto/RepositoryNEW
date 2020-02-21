//package br.com.logic.financeiro.br.com.logic.services;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Scanner;
//
//@Service
//public class MenuLogadoDirecionamento {
//
//    @Autowired
//    private ClienteService banco;
//
//    @Autowired
//    private MenuLogado menuLogado;
//
//    public void validaRemoverConta(ContaBancaria contaLogada){
//        Scanner teclado = new Scanner(System.in);
//
//        System.out.println("Tem certeza que deseja remover a conta? (s/n)");
//        String validaRemocaoConta = teclado.nextLine();
//
//        if ("s".equals(validaRemocaoConta)) {
//
//            banco.remover(contaLogada);
//
//            System.out.println("Conta removida com sucesso.");
//
//        } else if ("n".equals(validaRemocaoConta)) {
//            menuLogado.menuLogadoCliente(contaLogada);
//        } else {
//            System.out.println("Comando inválido!\n");
//            menuLogado.menuLogadoCliente(contaLogada);
//        }
//    }
//
//    public void sacarDirecionamento(ContaBancaria contaLogada){
//        Scanner teclado = new Scanner(System.in);
//        System.out.println("Digite o valor que deseja sacar:\n");
//        Double saque = Double.parseDouble(teclado.nextLine());
//
//        Boolean saqueCliente = contaLogada.sacar(saque, contaLogada);
//
//        if (saqueCliente) {
//            System.out.println("Saque realizado com sucesso! \nSaldo: " + contaLogada.getSaldo());
//        } else {
//            System.out.println("Saldo indisponível para saque. \nSaldo: " + contaLogada.getSaldo());
//        }
//    }
//    public void depositarDirecionamento(ContaBancaria contaLogada){
//        Scanner teclado = new Scanner(System.in);
//        System.out.println("Digite o valor que deseja depositar:\n");
//        Double deposito = Double.parseDouble(teclado.nextLine());
//
//        System.out.println(contaLogada.depositar(deposito, contaLogada));
//    }
//
//    public void transferirDirecionamento(ContaBancaria contaLogada){
//        Scanner teclado = new Scanner(System.in);
//        System.out.println("Qual valor deseja transferir?\n");
//        Double valorTransferencia = Double.parseDouble(teclado.nextLine());
//
//        System.out.println("Para qual conta?\n");
//        Double contaTransferencia = Double.parseDouble(teclado.nextLine());
//
//        ContaBancaria contaBeneficiario = banco.findContaBancaria(contaTransferencia);
//
//        if (contaBeneficiario.getNumeroConta() == null) {
//            System.out.println("Conta Inválida!");
//        } else {
//            System.out.println(banco.transferencia(valorTransferencia, contaBeneficiario, contaLogada));
//        }
//    }
//}

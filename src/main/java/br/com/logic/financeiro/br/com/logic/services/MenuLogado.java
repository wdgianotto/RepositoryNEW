//package br.com.logic.financeiro.br.com.logic.services;
//
//import br.com.logic.financeiro.br.com.logic.vo.ContaBancaria;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Scanner;
//
//@Service
//public class MenuLogado {
//
//    @Autowired
//    private ClienteService banco;
//
//    @Autowired
//    private MenuInicial menuInicial;
//
//    public void menuLogadoCliente(ContaBancaria contaLogada){
//        Scanner teclado = new Scanner(System.in);
//
//            System.out.println("Menu: \n" +
//                    "1: Remover Conta \n" +
//                    "2: Gerar Relatório \n" +
//                    "3: Sacar \n" +
//                    "4: Depositar \n" +
//                    "5: Transferir \n" +
//                    "6: Finalizar");
//
//            Integer menuLogado = Integer.parseInt(teclado.nextLine());
//
//        switch (menuLogado) {
//            case 1:
//                menuLogadoDirecionamento.validaRemoverConta(contaLogada);
//                menuInicial.menuInicialCliente();
//                break;
//            case 2:
//                System.out.println(contaLogada.gerarRelatorioContaLogada(contaLogada));
//                menuLogadoCliente(contaLogada);
//                break;
//            case 3:
//                menuLogadoDirecionamento.sacarDirecionamento(contaLogada);
//                menuLogadoCliente(contaLogada);
//                break;
//            case 4:
//                menuLogadoDirecionamento.depositarDirecionamento(contaLogada);
//                menuLogadoCliente(contaLogada);
//                break;
//            case 5:
//                menuLogadoDirecionamento.transferirDirecionamento(contaLogada);
//                menuLogadoCliente(contaLogada);
//                break;
//
//            case 6:
//                System.out.println("Obrigado!");
//                menuInicial.menuInicialCliente();
//                break;
//        }
//    }
//}

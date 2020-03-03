package br.com.logic.financeiro.br.com.logic.services;

import br.com.logic.financeiro.br.com.logic.domain.Conta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class MenuLogado {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private MenuInicial menuInicial;

    @Autowired
    private BancoService bancoService;

    public void menuLogadoCliente(Conta conta) {
        Scanner teclado = new Scanner(System.in);

            System.out.println("Menu: \n" +
                    "1: Remover Conta \n" +
                    "2: Gerar Relatório \n" +
                    "3: Sacar \n" +
                    "4: Depositar \n" +
                    "5: Transferir \n" +
                    "6: Finalizar");

            Integer menuLogado = Integer.parseInt(teclado.nextLine());

        switch (menuLogado) {
            case 1:
                bancoService.excluirConta(conta.getId(), conta);
                menuInicial.menuInicialCliente();
                break;
            case 2:
                bancoService.gerarRelatorioCliente(conta);
                break;
            case 3:
                bancoService.sacar(conta, bancoService.obterValorSaque());
                menuLogadoCliente(conta);
                break;
            case 4:
                bancoService.depositar(conta, bancoService.obterValorDeposito());
                menuLogadoCliente(conta);
                break;
            case 5:
                bancoService.transferir(conta, bancoService.obterValorTransferencia());
                menuLogadoCliente(conta);
                break;

            case 6:
                System.out.println("Obrigado!");
                menuInicial.menuInicialCliente();
                break;
        }
    }
}

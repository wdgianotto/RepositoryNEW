package br.com.logic.financeiro.br.com.logic.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class MenuInicial {

    @Autowired
    private ClienteService clienteService;

//    @Autowired
//    private LogarContaCliente logarContaCliente;

    public void menuInicialCliente() throws Exception {

        Scanner teclado = new Scanner(System.in);

        System.out.println("Menu: \n" +
                    "1: Criar Conta \n" +
                    "2: Selecionar Conta \n");

            Integer menu = Integer.parseInt(teclado.nextLine());

            if(menu == 1){
                clienteService.coletarDadosClienteCriarConta();
            }
            else if(menu == 2){
                clienteService.coletarDadosSelecionarConta();
            }else{
                System.out.println("Comando inválido");
                menuInicialCliente();
            }
    }

}

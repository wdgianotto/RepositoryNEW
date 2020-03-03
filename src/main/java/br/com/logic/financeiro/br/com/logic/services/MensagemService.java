package br.com.logic.financeiro.br.com.logic.services;

import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class MensagemService {

    Scanner teclado = new Scanner(System.in);


    public Integer enviarMensagemConsumoInteger(String mensagem){
        try{
            return Integer.parseInt(geradorMensagemComConsumo(mensagem));
        }catch (NumberFormatException e){
            enviarMensagemFormatoIncorreto();
            return enviarMensagemConsumoInteger(mensagem);
        }
    }

    public Double enviarMensagemConsumoDouble(String mensagem){
        try{
            return Double.parseDouble(geradorMensagemComConsumo(mensagem));
        }catch (NumberFormatException e){
            enviarMensagemFormatoIncorreto();
            return enviarMensagemConsumoDouble(mensagem);
        }
    }

    public String enviarMensagemConsumoString(String mensagem){
        return geradorMensagemComConsumo(mensagem);
    }

    private void enviarMensagemFormatoIncorreto(){
        System.out.println("Formato de informação incorreta!\n");
    }

    private String geradorMensagemComConsumo(String mensagem){
        System.out.println(mensagem);
        return teclado.nextLine();
    }
}

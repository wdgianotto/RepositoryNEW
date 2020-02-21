package br.com.logic.financeiro.br.com.logic.services;

import br.com.logic.financeiro.br.com.logic.repositories.BancoRepository;
import br.com.logic.financeiro.br.com.logic.repositories.ClienteRepository;
import br.com.logic.financeiro.br.com.logic.repositories.ContaRepository;
import br.com.logic.financeiro.br.com.logic.repositories.TipoContaRepository;
import br.com.logic.financeiro.br.com.logic.vo.Banco;
import br.com.logic.financeiro.br.com.logic.vo.Cliente;
import br.com.logic.financeiro.br.com.logic.vo.Conta;
import br.com.logic.financeiro.br.com.logic.vo.TipoConta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class ClienteService {

    @Autowired
    private MenuInicial menuInicial;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private BancoRepository bancoRepository;

    @Autowired
    private TipoContaRepository tipoContaRepository;

    Scanner teclado = new Scanner(System.in);

                                /*          COLETA DADOS PARA CRIAÇÃO DA CONTA          */
    public void coletarDadosClienteCriarConta() throws Exception {

        List<Conta> listaContaCliente;

        System.out.println("Em qual banco deseja criar a conta?\n" +
                "1: Santander\n" +
                "2: Bradesco");
        Integer bancoEscolhido = Integer.parseInt(teclado.nextLine());

        System.out.println("Você gostaria de criar uma conta corrente ou uma conta poupança?\n" +
                "1: Conta Corrente\n" +
                "2: Conta Poupança");
        Integer tipoContaEscolhida = Integer.parseInt(teclado.nextLine());

        System.out.println("Digite a conta: \n");
        Integer contaCliente = Integer.parseInt(teclado.nextLine());

        //VALIDA SE A CONTA EXISTE
        listaContaCliente = contaRepository.findAllByNumeroContaAndIdBancoAndIdTipoConta(contaCliente, tipoContaEscolhida, bancoEscolhido);

        //SE NÃO EXISTIR, SOLICITA O RESTANTE DAS INFORMAÇÕES
        if (listaContaCliente.isEmpty()) {
            System.out.println("Digite o nome: \n");
            String nomeCliente = teclado.nextLine();

            System.out.println("Digite o saldo: \n");
            Double saldoCliente = Double.parseDouble(teclado.nextLine());

            System.out.println("Digite o CPF: \n");
            String cpfCliente = teclado.nextLine();

            System.out.println("Conta válida, encaminhado para salvar no banco de dados!");
            salvarCliente(bancoEscolhido, tipoContaEscolhida, contaCliente, nomeCliente, saldoCliente, cpfCliente);

        } else {
            System.out.println("Conta já existe!");
            menuInicial.menuInicialCliente();
        }
    }
                        /*          COLETA DADOS PARA ACESSAR A CONTA          */
    public void coletarDadosSelecionarConta() throws Exception {
        try {
            System.out.println("Digite sua conta:\n");
            Integer contaCliente = Integer.parseInt(teclado.nextLine());

            List<Conta> listaContaCliente = buscarContas(contaCliente);

            Integer contador = 0;

            if (listaContaCliente.isEmpty()) {
                System.out.println("Conta inexistente!");
            } else {
                System.out.println("Escolha uma conta:\n");
                for (Conta conta : listaContaCliente) {
                    System.out.println(++contador + ": " + "banco: " + conta.getBanco().getNome() + ", Tipo da conta: "
                            + conta.getTipoConta().getTipoConta() + ", Saldo: " + conta.getSaldo());
                }
                Integer contaSelecionada = Integer.parseInt(teclado.nextLine());
                Conta conta = listaContaCliente.get(contaSelecionada - 1);
            }
        }catch(Exception e){
            throw e;
        }

    }

    @Transactional
    public void salvarCliente(Integer idBanco, Integer idTipoConta, Integer contaCliente,
                              String nomeCliente, Double saldo, String cpf) throws Exception {
        try {
            Cliente validaCliente = obterCliente(cpf);
            if (validaCliente == null) {
                Cliente cliente = new Cliente(null, nomeCliente, cpf);
                clienteRepository.save(cliente);
                validaCliente = cliente;
            }
                Banco banco = obterBanco(idBanco);
                TipoConta tipoConta = obterTipoConta(idTipoConta);

                contaRepository.save(new Conta(null, nomeCliente, cpf, banco.getNome(), banco.getAg(), contaCliente, saldo, validaCliente, banco, tipoConta));

            System.out.println("Conta salva com sucesso!");
            menuInicial.menuInicialCliente();
            }catch(Exception e){
                throw e;
            }
    }


    public Banco obterBanco(Integer idBanco) throws Exception{
        return bancoRepository.findById(idBanco).orElseThrow(() -> new Exception(""));
    }

    public TipoConta obterTipoConta(Integer idTipoConta) throws Exception{
        return tipoContaRepository.findById(idTipoConta).orElseThrow(() -> new Exception(""));
    }

    public Cliente obterCliente(String cpf){
        Optional cliente = clienteRepository.findByCpf(cpf);
        Cliente ret = null;
        if(cliente.isPresent()){
            ret = (Cliente) cliente.get();
        }
        return ret;

    }

    public List<Conta> buscarContas(Integer numeroConta) throws Exception{
        List<Conta> listaContaCliente = contaRepository.findByNumeroConta(numeroConta).orElseThrow(() -> new Exception(""));
        return listaContaCliente;
    }
}

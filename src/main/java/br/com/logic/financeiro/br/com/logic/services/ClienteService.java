package br.com.logic.financeiro.br.com.logic.services;

import br.com.logic.financeiro.br.com.logic.repositories.BancoRepository;
import br.com.logic.financeiro.br.com.logic.repositories.ClienteRepository;
import br.com.logic.financeiro.br.com.logic.repositories.ContaRepository;
import br.com.logic.financeiro.br.com.logic.repositories.TipoContaRepository;
import br.com.logic.financeiro.br.com.logic.domain.Banco;
import br.com.logic.financeiro.br.com.logic.domain.Cliente;
import br.com.logic.financeiro.br.com.logic.domain.Conta;
import br.com.logic.financeiro.br.com.logic.domain.TipoConta;
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

    @Autowired
    private MenuLogado menuLogado;

    @Autowired
    private MensagemService mensagemService;

    @Autowired
    private CriarContaService criarContaService;

    Scanner teclado = new Scanner(System.in);

    public void criarContaOrquestrador(Conta conta){
        List<Conta> listaContaAux = criarContaService.validarCriacaoConta(conta.getBanco(),conta.getTipoConta(),
                conta.getNumeroConta());
        if(listaContaAux.isEmpty()){
            System.out.println("Conta válida, encaminhado para salvar no banco de dados!");
            salvarCliente(conta.getBanco(), conta.getTipoConta(), conta.getNumeroConta(),
                    conta.getSaldo(), conta.getCliente());
        }else{
            System.out.println("Conta já existe!");
            menuInicial.menuInicialCliente();
        }
    }

                                /*          COLETA DADOS PARA CRIAÇÃO DA CONTA          */
    public Banco menuEscolheBanco() {
        List<Banco> listaBancos = bancoRepository.findAll();
        Banco banco = null;
        for(Banco bancoAux : listaBancos){
                System.out.println(bancoAux.getId() + ": " + bancoAux.getNome());
        }
        Integer numeroBanco = mensagemService.enviarMensagemConsumoInteger("Em qual banco deseja criar a conta?\n");
        try{
        banco = obterBanco(numeroBanco);
        }catch(Exception e){
            System.out.println("Não foi possível encontrar o banco selecionado. idBanco: " + numeroBanco);
            menuEscolheBanco();
        }
        return banco;
    }

    public TipoConta menuEscolheTipoConta(){
        List<TipoConta> listaTipoConta = tipoContaRepository.findAll();
        TipoConta tipoConta = null;
        for(TipoConta tipoContaAux : listaTipoConta){
            System.out.println(tipoContaAux.getId() + ": " + tipoContaAux.getTipoConta());
        }
        Integer numeroTipoConta = mensagemService.enviarMensagemConsumoInteger("Você gostaria de criar uma conta corrente ou uma conta poupança?\n");
        try{
            tipoConta = obterTipoConta(numeroTipoConta);
        }catch(Exception e){
            System.out.println("Não foi possível encontrar o tipo da conta selecionado. idTipoConta: " + numeroTipoConta);
            menuEscolheTipoConta();
        }
        return tipoConta;
    }

    public Integer menuEscolheNumeroConta(){
        Integer numeroConta = mensagemService.enviarMensagemConsumoInteger("Digite a conta: \n");
        return numeroConta;
    }

    public String menuEscolheNomeCliente(){
        String nomeCliente = mensagemService.enviarMensagemConsumoString("Digite o nome: \n");
        return nomeCliente;
    }

    public Double menuEscolheSaldo(){
        Double saldoCliente = mensagemService.enviarMensagemConsumoDouble("Digite o saldo: \n");
        return saldoCliente;
    }

    public Cliente menuEscolheCPF(){
        String CPFCliente = mensagemService.enviarMensagemConsumoString("Digite o CPF: \n");
        Cliente cliente = obterCliente(CPFCliente);

        if(cliente == null){
            String nomeCliente = menuEscolheNomeCliente();
            cliente = new Cliente(null, nomeCliente, CPFCliente);
        }
        return cliente;
    }

                        /*          COLETA DADOS PARA ACESSAR A CONTA          */
    public Conta coletarDadosSelecionarConta(Integer contaCliente) {
        Conta conta = null;
        try{

            List<Conta> listaContaCliente = buscarContas(contaCliente);

            Integer contador = 0;

            if (listaContaCliente.isEmpty()) {
                conta = null;
            } else {
                System.out.println("Escolha uma conta:\n");
                for (Conta contaAux : listaContaCliente) {
                    System.out.println(++contador + ": " + "banco: " + contaAux.getBanco().getNome() + ", Tipo da conta: "
                            + contaAux.getTipoConta().getTipoConta() + ", Saldo: " + contaAux.getSaldo());
                }
                Integer contaSelecionada = Integer.parseInt(teclado.nextLine());
                conta = listaContaCliente.get(contaSelecionada - 1);
            }

        }
        catch(Exception e){
            System.out.println("Conta inexistente!");
            menuInicial.menuInicialCliente();
        }
        return conta;
    }

    @Transactional
    public void salvarCliente(Banco banco, TipoConta TipoConta, Integer contaCliente,
                              Double saldo, Cliente clienteAux) {

            try {
                clienteRepository.save(clienteAux);

                contaRepository.save(new Conta(null, contaCliente,
                        saldo, clienteAux, banco, TipoConta, 500.00));

                System.out.println("Conta salva com sucesso!");
                menuInicial.menuInicialCliente();
            }
            catch(Exception e){
                menuInicial.menuInicialCliente();
            }
    }


    public Banco obterBanco(Integer idBanco) throws Exception {
        return bancoRepository.findById(idBanco).orElseThrow(() -> new Exception(""));
    }

    public TipoConta obterTipoConta(Integer idTipoConta) throws Exception{
        return tipoContaRepository.findById(idTipoConta).orElseThrow(() -> new Exception(""));
    }

    public Cliente obterClienteById(Integer idCliente) throws Exception{
        return clienteRepository.findById(idCliente).orElseThrow(() -> new Exception(""));
    }

    public Cliente obterCliente(String cpf){
        Optional cliente = clienteRepository.findByCpf(cpf);
        Cliente ret = null;
        if(cliente.isPresent()){
            ret = (Cliente) cliente.get();
        }
        return ret;

    }

    public Conta obterConta(Banco banco, TipoConta tipoConta, Integer numeroConta,
                            Double saldoCliente, Cliente cliente){
        Conta conta = new Conta(null,numeroConta,saldoCliente,cliente,banco,tipoConta, 500.00);
        return conta;

    }

    public List<Conta> buscarContas(Integer numeroConta) throws Exception{
        List<Conta> listaContaCliente = contaRepository.findByNumeroConta(numeroConta).orElseThrow(() -> new Exception("Conta Inexistente"));
        return listaContaCliente;
    }


}

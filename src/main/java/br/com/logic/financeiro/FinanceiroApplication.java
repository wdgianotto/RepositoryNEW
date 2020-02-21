package br.com.logic.financeiro;

import br.com.logic.financeiro.br.com.logic.repositories.BancoRepository;
import br.com.logic.financeiro.br.com.logic.repositories.ClienteRepository;
import br.com.logic.financeiro.br.com.logic.repositories.ContaRepository;
import br.com.logic.financeiro.br.com.logic.repositories.TipoContaRepository;
import br.com.logic.financeiro.br.com.logic.services.MenuInicial;
import br.com.logic.financeiro.br.com.logic.vo.Banco;
import br.com.logic.financeiro.br.com.logic.vo.Cliente;
import br.com.logic.financeiro.br.com.logic.vo.Conta;
import br.com.logic.financeiro.br.com.logic.vo.TipoConta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;


@SpringBootApplication
public class FinanceiroApplication implements CommandLineRunner {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private BancoRepository bancoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private TipoContaRepository tipoContaRepository;

    @Autowired
    private MenuInicial menuInicial;

    public static void main(String[] args) {
        SpringApplication.run(FinanceiroApplication.class, args);
    }



    @Override
    public void run(String... args) throws Exception {
        List<Conta> listaContaCliente;

        Banco bancoSantander = new Banco(null, "Santander", "00055");
        Banco bancoBradesco = new Banco(null, "Bradesco", "00065");
        bancoRepository.saveAll(Arrays.asList(bancoSantander,bancoBradesco));

        TipoConta tipoConta = new TipoConta(null,"Conta Corrente");
        TipoConta tipoContaPoupanca = new TipoConta(null,"Conta Poupanca");
        tipoContaRepository.saveAll(Arrays.asList(tipoConta,tipoContaPoupanca));
;
        Cliente cliente = new Cliente(null, "wed", "1");
        clienteRepository.save(cliente);

        contaRepository.save(new Conta(null, "wed", "1", "Santander","00055", 123456, 400D,cliente,bancoSantander,tipoConta));

        menuInicial.menuInicialCliente();

    }
}
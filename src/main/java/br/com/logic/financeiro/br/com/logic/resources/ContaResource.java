package br.com.logic.financeiro.br.com.logic.resources;

import br.com.logic.financeiro.br.com.logic.domain.Conta;
import br.com.logic.financeiro.br.com.logic.domain.DTO.ContaDTO;
import br.com.logic.financeiro.br.com.logic.domain.DTO.ContaDTOCriarConta;
import br.com.logic.financeiro.br.com.logic.services.BancoService;
import br.com.logic.financeiro.br.com.logic.services.ClienteService;
import br.com.logic.financeiro.br.com.logic.services.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/bancoservice")
public class ContaResource {

    @Autowired
    private BancoService bancoService;

    @Autowired
    private ContaService contaService;

    @Autowired
    private ClienteService clienteService;

    @RequestMapping(value = "/criarconta/", method = RequestMethod.POST)
    public ResponseEntity<Void> criarConta(@RequestBody ContaDTOCriarConta conta){
        clienteService.criarContaOrquestrador(contaService.converteDTOContaCriarConta(conta));
        return ResponseEntity.noContent().build();
    }
}

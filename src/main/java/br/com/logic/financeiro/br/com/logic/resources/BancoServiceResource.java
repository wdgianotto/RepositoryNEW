package br.com.logic.financeiro.br.com.logic.resources;

import br.com.logic.financeiro.br.com.logic.domain.Conta;
import br.com.logic.financeiro.br.com.logic.domain.DTO.ContaDTO;
import br.com.logic.financeiro.br.com.logic.services.BancoService;
import br.com.logic.financeiro.br.com.logic.services.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/bancoservice")
public class BancoServiceResource {

    @Autowired
    private BancoService bancoService;

    @Autowired
    private ContaService contaService;

    @RequestMapping(value = "/depositar/{valor}", method = RequestMethod.PATCH)
    public ResponseEntity<Void> depositar(@RequestBody ContaDTO conta, @PathVariable Double valor){
        bancoService.depositar(contaService.converteDTOConta(conta), valor);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/sacar/{valor}", method = RequestMethod.PATCH)
    public ResponseEntity<Void> sacar(@RequestBody ContaDTO conta, @PathVariable Double valor){
        bancoService.sacar(contaService.converteDTOConta(conta), valor);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletar(@RequestBody Conta conta){
        bancoService.excluirConta(conta);
        return ResponseEntity.noContent().build();
    }

//    @RequestMapping(value = "/{transferir}/{id}", method = RequestMethod.DELETE)
//    public ResponseEntity<Void> transferir(@RequestBody Conta conta, @PathVariable Integer id){
//        bancoService.excluirConta(id, conta);
//        return ResponseEntity.noContent().build();
//    }

    @RequestMapping(value = "/relatorio", method = RequestMethod.GET)
    public ResponseEntity<Void> relatorio(@RequestBody Conta conta){
        bancoService.gerarRelatorioCliente(conta);
        return ResponseEntity.noContent().build();
    }
}

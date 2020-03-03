package br.com.logic.financeiro.br.com.logic.resources;

import br.com.logic.financeiro.br.com.logic.domain.Conta;
import br.com.logic.financeiro.br.com.logic.services.BancoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/bancoservice")
public class BancoServiceResource {

    @Autowired
    private BancoService bancoService;

//    @RequestMapping(value = "/{depositar}/{valor}", method = RequestMethod.PUT)
//    public ResponseEntity<Void> depositar(@RequestBody Conta conta, @PathVariable Double valor){
//        bancoService.depositar(conta);
//        return ResponseEntity.noContent().build();
//    }

//    @RequestMapping(value = "{sacar}/{valor}", method = RequestMethod.PUT)
//    public ResponseEntity<Void> sacar(@RequestBody Conta conta, @PathVariable Double valor){
//        bancoService.sacar(conta);
//        return ResponseEntity.noContent().build();
//    }
}

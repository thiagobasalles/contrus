package br.com.netdeal.contrusinfrastructurewebapi.restcontroller;

import java.util.Date;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.netdeal.contrusapplication.usecases.cadastrar_colaborador_usecase.CadastrarColaboradorCommand;
import org.springframework.web.bind.annotation.PostMapping;

@RequestMapping(value = "api/contrus/cadastrar-colaborador", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class CadastrarColaboradorRestController {

    private final CommandGateway commandGateway;

    public CadastrarColaboradorRestController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping()
    public ResponseEntity<Void> post(String nome, String senha, String telefone, String email, Date dataNascimento, 
    long colaboradorHierarquiaSuperiorId){
        try{
            commandGateway.send(new CadastrarColaboradorCommand(colaboradorHierarquiaSuperiorId,nome,senha, telefone, email,dataNascimento));
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

}

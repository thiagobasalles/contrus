package br.com.netdeal.contrusinfrastructurewebapi.restcontroller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.netdeal.contrusapplication.usecases.listar_colaboradores_hierarquia.ListarColaboradoresHierarquiaCommand;
import br.com.netdeal.contrusdomain.dtos.ColaboradorHierarquiaResponseDto;

@RequestMapping(value = "api/contrus/listar-colaboradores-hierarquia", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class ListarColaboradoresHierarquiaRestController {
    private final CommandGateway commandGateway;

    public ListarColaboradoresHierarquiaRestController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @GetMapping()
    public ResponseEntity<List<ColaboradorHierarquiaResponseDto>> get(){
        try{
            CompletableFuture<List<ColaboradorHierarquiaResponseDto>> future = commandGateway.send(new ListarColaboradoresHierarquiaCommand());
            return ResponseEntity.ok(future.get());
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}

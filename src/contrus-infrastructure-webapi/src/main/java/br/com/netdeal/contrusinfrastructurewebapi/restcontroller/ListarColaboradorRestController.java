package br.com.netdeal.contrusinfrastructurewebapi.restcontroller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.netdeal.contrusapplication.usecases.listar_colaborador.ListarColaboradorCommand;
import br.com.netdeal.contrusdomain.dtos.ColaboradorResponseDto;

@RequestMapping(value = "api/contrus/listar-colaboradores", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class ListarColaboradorRestController {
    private final CommandGateway commandGateway;

    public ListarColaboradorRestController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @GetMapping()
    public ResponseEntity<List<ColaboradorResponseDto>> get(){
        CompletableFuture<List<ColaboradorResponseDto>> future = commandGateway.send(new ListarColaboradorCommand());
        try{
            return ResponseEntity.ok(future.get());
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}

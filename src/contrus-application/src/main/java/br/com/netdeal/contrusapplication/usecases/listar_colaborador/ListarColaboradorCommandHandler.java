package br.com.netdeal.contrusapplication.usecases.listar_colaborador;

import java.util.ArrayList;
import java.util.List;

import org.axonframework.commandhandling.CommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.netdeal.contrusapplication.interfaces.repositories.IColaboradorRepository;
import br.com.netdeal.contrusdomain.dtos.ColaboradorResponseDto;
import br.com.netdeal.contrusdomain.models.Colaborador;

@Component
public class ListarColaboradorCommandHandler {
    
    @Autowired
    private IColaboradorRepository colaboradorRepository;

    @CommandHandler
    public List<ColaboradorResponseDto> handle(ListarColaboradorCommand command) {
        var listaColaboradores = colaboradorRepository.findAll();

        List<ColaboradorResponseDto> response = new ArrayList<ColaboradorResponseDto>();
        for (Colaborador colaborador : listaColaboradores) {
            var colabResponseDto = new ColaboradorResponseDto();
            colabResponseDto.colaboradorId = colaborador.getId();
            colabResponseDto.nomeColaborador = colaborador.getNome();
            response.add(colabResponseDto);
        }
        return response;
    }

}

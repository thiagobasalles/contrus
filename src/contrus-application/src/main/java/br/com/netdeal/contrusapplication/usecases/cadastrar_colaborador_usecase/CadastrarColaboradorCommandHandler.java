package br.com.netdeal.contrusapplication.usecases.cadastrar_colaborador_usecase;

import org.axonframework.commandhandling.CommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.netdeal.contrusapplication.interfaces.repositories.IColaboradorRepository;
import br.com.netdeal.contrusdomain.SeedWorks.BusinessException;
import br.com.netdeal.contrusdomain.models.Colaborador;
import br.com.netdeal.contrusdomain.models.Hierarquia;

@Component
public class CadastrarColaboradorCommandHandler {
    
    @Autowired
    private IColaboradorRepository colaboradorRepository;

    @CommandHandler
    public void handle(CadastrarColaboradorCommand command) throws BusinessException {
        var colab = new Colaborador();

        if(command.getNome().isEmpty()){
            throw new BusinessException("Nome vazio ou inválido.");
        }
        if(command.getSenha().isEmpty()){
            throw new BusinessException("Senha vazio ou inválido.");
        }

        var hierar = new Hierarquia();
        if(command.getColaboradorHierarquiaSuperiorId()==0)
            hierar.setValorHierarquia(0);
        else{
            var colaboradorSuperior = colaboradorRepository.findById(command.getColaboradorHierarquiaSuperiorId());
            hierar.setValorHierarquia(colaboradorSuperior.getHierarquia().getValorHierarquia()+1);      
            hierar.setHierarquiaSuperiorColaborador(colaboradorSuperior);
        }
        colab.setHierarquia(hierar);
        
        colab.setNome(command.getNome());
        colab.setSenha(command.getSenha());
        colab.setEmail(command.getEmail());
        colab.setDataNascimento(command.getDataNascimento());
        colab.setTelefone(command.getTel());
        colab.setHierarquia(hierar);

        colaboradorRepository.saveAndFlush(colab);

    }   

}

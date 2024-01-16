package br.com.netdeal.contrusinfrastructurewebapi.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import br.com.netdeal.contrusapplication.interfaces.repositories.IColaboradorRepository;
import br.com.netdeal.contrusapplication.interfaces.repositories.IHierarquiaRepository;
import br.com.netdeal.contrusinfrastructuredata.repositories.ColaboradorRepository;
import br.com.netdeal.contrusinfrastructuredata.repositories.HierarquiaRepository;

@Configuration
@ComponentScan
public class RepositoryBean {
    
    @Bean
    public IColaboradorRepository colaboradorRepository() {
        return new ColaboradorRepository();
    }
    
    @Bean
    public IHierarquiaRepository hierarquiaRepository() {
        return new HierarquiaRepository();
    }

}
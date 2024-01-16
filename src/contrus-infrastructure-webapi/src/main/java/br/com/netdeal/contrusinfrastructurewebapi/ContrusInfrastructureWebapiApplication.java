package br.com.netdeal.contrusinfrastructurewebapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.com.netdeal.contrusdomain.models.Colaborador;

@SpringBootApplication
@ComponentScans({
	@ComponentScan(basePackages = "br.com.netdeal.*"),
})
@EntityScan(basePackageClasses = Colaborador.class)
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = {"br.com.netdeal.contrusinfrastructuredata.*"})
public class ContrusInfrastructureWebapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContrusInfrastructureWebapiApplication.class, args);
	}

}

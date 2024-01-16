package br.com.netdeal.contrusinfrastructuredata.configs;


import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import liquibase.integration.spring.SpringLiquibase;

@Configuration
@ComponentScan
public class DataSourceBean {
    
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver"); // Altere para o driver do seu banco de dados
        dataSource.setUrl("jdbc:mysql://localhost:30000/contrus?zeroDateTimeBehavior=convertToNull&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false"); // URL do banco de dados
        dataSource.setUsername("root"); // Nome de usuário
        dataSource.setPassword("root"); // Senha
        
        return dataSource;
    }

    @Bean
    public SpringLiquibase liquibase(DataSource dataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog("classpath:db/changelog/db.changelog-master.xml");
        return liquibase;
    }
    
}

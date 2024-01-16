package br.com.netdeal.contrusinfrastructuredata.interfaces;

import org.springframework.stereotype.Repository;

import br.com.netdeal.contrusdomain.models.Colaborador;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface  IColaboradorJpaRepository extends JpaRepository<Colaborador, Long> {

}
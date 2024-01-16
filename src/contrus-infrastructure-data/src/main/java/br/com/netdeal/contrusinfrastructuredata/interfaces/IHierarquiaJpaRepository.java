package br.com.netdeal.contrusinfrastructuredata.interfaces;

import org.springframework.stereotype.Repository;

import br.com.netdeal.contrusdomain.models.Hierarquia;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface  IHierarquiaJpaRepository extends JpaRepository<Hierarquia, Long> {

}
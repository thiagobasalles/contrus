package br.com.netdeal.contrusinfrastructuredata.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.netdeal.contrusapplication.interfaces.repositories.IHierarquiaRepository;
import br.com.netdeal.contrusdomain.models.Hierarquia;
import br.com.netdeal.contrusinfrastructuredata.interfaces.IHierarquiaJpaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;


public class HierarquiaRepository implements IHierarquiaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private IHierarquiaJpaRepository jpaRepository;

    @Override
    public Hierarquia buscarPorColaboradorId(long id) {
        try{
            return entityManager.createQuery("SELECT h FROM Hierarquia h WHERE h.colaboradorId = :colaboradorId", Hierarquia.class)
                    .setParameter("colaboradorId", id)
                    .getSingleResult();
        }  catch(Exception e){
            return null;
        }
    }

    @Override
    public Hierarquia saveAndFlush(Hierarquia entity) {
        return jpaRepository.saveAndFlush(entity);
    }

    @Override
    public Hierarquia save(Hierarquia entity) {
        return jpaRepository.save(entity);
    }

    @Override
    public List<Hierarquia> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public Hierarquia findById(long id) {
        return jpaRepository.findById(id).get();
    }


}
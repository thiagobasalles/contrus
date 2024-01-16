package br.com.netdeal.contrusinfrastructuredata.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import br.com.netdeal.contrusapplication.interfaces.repositories.IColaboradorRepository;
import br.com.netdeal.contrusdomain.models.Colaborador;
import br.com.netdeal.contrusinfrastructuredata.interfaces.IColaboradorJpaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;


public class ColaboradorRepository implements IColaboradorRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private IColaboradorJpaRepository jpaRepository;


    @Transactional
    @Override
    public Colaborador saveAndFlush(Colaborador entity) {
        return jpaRepository.saveAndFlush(entity);
    }

    @Override
    public List<Colaborador> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public Colaborador findById(long id) {
        return jpaRepository.findById(id).get();
    }

    @Override
    public Colaborador save(Colaborador entity) {
        return jpaRepository.save(entity);
    }

    @Override
    public Colaborador getByHierarquiaId(long hierarquiaId) {
        TypedQuery<Colaborador> query = entityManager.createQuery("SELECT c FROM Colaborador c WHERE c.hierarquia.id = :hierarquiaId", Colaborador.class);
        query.setParameter("hierarquiaId", hierarquiaId);
        return query.getSingleResult();
    }

    @Override
    public List<Colaborador> bustarTodosComHierarquiaZero() {
        TypedQuery<Colaborador> query = entityManager.createQuery("SELECT c FROM Colaborador c JOIN Hierarquia h on h.id = c.hierarquia.id WHERE h.valorHierarquia=0", Colaborador.class);
        return query.getResultList();
    }

    @Override
    public List<Colaborador> buscarColaboradorHierarquiaSeguinte(long idColaborador, int indexHierarquiaSeguinte) {
        TypedQuery<Colaborador> query = entityManager.createQuery("SELECT c FROM Colaborador c JOIN Hierarquia h on h.id = c.hierarquia.id WHERE h.valorHierarquia=:indexHierarquiaSeguinte and hierarquiaSuperiorColaborador.id = :idColaborador", Colaborador.class);
        query.setParameter("idColaborador", idColaborador);
        query.setParameter("indexHierarquiaSeguinte", indexHierarquiaSeguinte);
        return query.getResultList();
    }

}
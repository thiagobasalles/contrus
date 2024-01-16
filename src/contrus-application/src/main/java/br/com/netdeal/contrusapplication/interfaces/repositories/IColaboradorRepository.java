package br.com.netdeal.contrusapplication.interfaces.repositories;

import java.util.List;

import br.com.netdeal.contrusdomain.models.Colaborador;

public interface IColaboradorRepository extends IBaseRepository<Colaborador>{

    Colaborador getByHierarquiaId(long hierarquiaId);

    List<Colaborador> bustarTodosComHierarquiaZero();

    List<Colaborador> buscarColaboradorHierarquiaSeguinte(long idColaborador, int indexHierarquiaSeguinte);
}

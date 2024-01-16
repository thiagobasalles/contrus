package br.com.netdeal.contrusapplication.interfaces.repositories;

import br.com.netdeal.contrusdomain.models.Hierarquia;

public interface IHierarquiaRepository extends IBaseRepository<Hierarquia>{
    Hierarquia buscarPorColaboradorId(long id);
}

package br.com.renato.lst_rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.renato.lst_rest.models.ClienteModel;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteModel, Long>{

}

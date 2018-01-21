package br.com.studo.repository;

import br.com.studo.domain.Professor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepositoty extends CrudRepository<Professor, Long> {

}

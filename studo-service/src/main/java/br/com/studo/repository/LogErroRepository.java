package br.com.studo.repository;

import br.com.studo.domain.LogErro;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogErroRepository extends CrudRepository<LogErro, Long> {
}

package br.com.studo.repository;

import br.com.studo.domain.Atividade;
import org.springframework.data.repository.CrudRepository;

public interface AtividadeRepository extends CrudRepository<Atividade,Long> {
}

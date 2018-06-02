package br.com.studo.repository;

import br.com.studo.domain.Questao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestaoRepository extends CrudRepository<Questao, Long> {
}

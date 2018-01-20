package br.com.studo.repository;

import br.com.studo.domain.Disciplina;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplinaRepository extends CrudRepository<Disciplina, Long> {

    Page<Disciplina> findByDescricaoContaining(String descricao, Pageable pageable);

    @Query(value = "SELECT count(*) > 0 FROM Disciplina WHERE descricao = ?1")
    Boolean buscaDisciplinaPorNome(String descricao);
}

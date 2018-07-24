package br.com.studo.repository;

import br.com.studo.domain.Disciplina;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@JaversSpringDataAuditable
public interface DisciplinaRepository extends CrudRepository<Disciplina, Long> {

    Page<Disciplina> findByDescricaoContainingIgnoreCase(String descricao, Pageable pageable);

    @Query(value = "SELECT count(*) > 0 FROM Disciplina WHERE descricao = ?1")
    Boolean buscaDisciplinaPorNome(String descricao);

    @Query("SELECT count(*) FROM Disciplina")
    Integer quantidade();
}

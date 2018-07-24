package br.com.studo.repository;

import br.com.studo.domain.Aluno;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@JaversSpringDataAuditable
public interface AlunoRepository extends CrudRepository<Aluno, Long> {

    Page<Aluno> findByNomeStartingWithIgnoreCase(String nome, Pageable pageable);

    @Query("SELECT count(*) FROM Aluno")
    Integer quantidade();

}

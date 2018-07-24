package br.com.studo.repository;

import br.com.studo.domain.Turma;
import br.com.studo.domain.enums.Periodo;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@JaversSpringDataAuditable
public interface TurmaRepository extends CrudRepository<Turma, Long> {

    @Query(value = "SELECT turma FROM Turma turma WHERE turma.periodo IN ?1 AND turma.ano = ?2 ")
    Page<Turma> findPeriodoAndAno(List<Periodo> periodos, Integer ano, Pageable pageable);

    Turma findByNumeroTurma(String numTurma);

    @Query("SELECT count(*) > 0 FROM Turma WHERE numeroTurma = ?1")
    Boolean findTurmaCadastrada(String numTruma);

    @Query("SELECT count(*) FROM Turma WHERE ano = ?1")
    Integer quantidade(int ano);

}

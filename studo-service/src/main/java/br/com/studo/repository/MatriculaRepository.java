package br.com.studo.repository;

import br.com.studo.domain.Matricula;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatriculaRepository extends CrudRepository<Matricula, Long> {

    List<Matricula> findByAlunoCodigo(Long codigo);

    @Query("SELECT count(m) > 0 FROM Matricula m WHERE m.aluno.codigo = ?1 AND m.turma.codigo = ?2")
    Boolean findByAlunoMatriculado(Long codAluno, Long codTumra);
}

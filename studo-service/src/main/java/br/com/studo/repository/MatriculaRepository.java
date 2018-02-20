package br.com.studo.repository;

import br.com.studo.domain.Matricula;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MatriculaRepository extends CrudRepository<Matricula, Long> {

    List<Matricula> findByAlunoCodigo(Long codigo);
}

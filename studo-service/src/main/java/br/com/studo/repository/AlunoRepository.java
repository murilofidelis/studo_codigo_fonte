package br.com.studo.repository;

import br.com.studo.domain.Aluno;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends CrudRepository<Aluno, Long> {

    Page<Aluno> findByNomeStartingWithIgnoreCase(String nome, Pageable pageable);

}
package br.com.studo.repository;

import br.com.studo.domain.Professor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepositoty extends CrudRepository<Professor, Long> {

    Page<Professor> findByNomeStartingWithIgnoreCase(String nome, Pageable pageable);

    @Query("SELECT count(*) > 0 FROM Professor WHERE cpf = ?1")
    Boolean findByCpfCadastrado(String cpf);

    @Query("SELECT count(*) FROM Professor")
    Integer quantidade();

}

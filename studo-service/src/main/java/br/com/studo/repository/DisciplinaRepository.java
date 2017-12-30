package br.com.studo.repository;

import br.com.studo.domain.Disciplina;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {

    public Page<Disciplina> findByDescricaoContaining(String descricao, Pageable pageable);
}

package br.com.studo.repository;

import br.com.studo.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepositoty extends JpaRepository<Pessoa, Long> {

}

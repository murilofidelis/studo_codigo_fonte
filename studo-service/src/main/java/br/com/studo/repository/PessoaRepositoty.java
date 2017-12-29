package br.com.studo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.studo.domain.Pessoa;

public interface PessoaRepositoty extends JpaRepository<Pessoa, Long> {

}

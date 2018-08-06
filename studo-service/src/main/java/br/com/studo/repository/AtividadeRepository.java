package br.com.studo.repository;

import br.com.studo.domain.Atividade;
import br.com.studo.repository.custom.AtividadeRepositoryCustom;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtividadeRepository extends CrudRepository<Atividade, Long>, JpaSpecificationExecutor<Atividade>, AtividadeRepositoryCustom {
}

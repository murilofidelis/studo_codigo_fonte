package br.com.studo.repository;

import br.com.studo.domain.Documento;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DocumentoRepository extends CrudRepository<Documento, Long> {

    List<Documento> findByAlunoCodigo(Long codigoAluno);

}

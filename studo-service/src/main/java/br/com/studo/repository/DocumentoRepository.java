package br.com.studo.repository;

import br.com.studo.domain.Documento;
import br.com.studo.domain.dto.DocumentoDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DocumentoRepository extends CrudRepository<Documento, Long> {

    @Query("SELECT new br.com.studo.domain.dto.DocumentoDTO(d.codigo, d.nome) FROM Documento d WHERE d.aluno.codigo = ?1")
    List<DocumentoDTO> findByAluno(Long codigo);
}

package br.com.studo.repository.custom;

import br.com.studo.domain.dto.AtividadeDTO;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface AtividadeRepositoryCustom {

    List<AtividadeDTO> buscaAtividades(LocalDateTime dataInicio, LocalDateTime dataFim, Long codProfessor, Pageable pageable);

    Long quantidade(LocalDateTime dataInicio, LocalDateTime dataFim, Long codProfessor, Pageable pageable);
}

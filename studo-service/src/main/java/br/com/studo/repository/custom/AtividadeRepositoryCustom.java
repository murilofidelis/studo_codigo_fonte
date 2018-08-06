package br.com.studo.repository.custom;

import br.com.studo.domain.dto.AtividadeConsultaDTO;
import org.springframework.data.domain.Pageable;

import java.sql.Date;
import java.util.List;

public interface AtividadeRepositoryCustom {

    List<AtividadeConsultaDTO> buscaAtividades(Date dataInicio, Date dataFim, Long codProfessor, Pageable pageable);

    Long quantidade(Date dataInicio, Date dataFim, Long codProfessor);
}

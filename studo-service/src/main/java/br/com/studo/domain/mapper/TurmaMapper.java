package br.com.studo.domain.mapper;

import br.com.studo.domain.Turma;
import br.com.studo.domain.dto.TurmaDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TurmaMapper extends AbstractMapper<Turma, TurmaDTO> {
}

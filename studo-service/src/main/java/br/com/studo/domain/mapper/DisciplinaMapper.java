package br.com.studo.domain.mapper;

import br.com.studo.domain.Disciplina;
import br.com.studo.domain.dto.DisciplinaDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DisciplinaMapper extends AbstractMapper<Disciplina, DisciplinaDTO> {
}

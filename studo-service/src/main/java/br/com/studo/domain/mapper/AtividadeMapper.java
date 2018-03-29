package br.com.studo.domain.mapper;

import br.com.studo.domain.Atividade;
import br.com.studo.domain.dto.AtividadeDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {
        ProfessorMapper.class,
        DisciplinaMapper.class
})
public interface AtividadeMapper extends AbstractMapper<Atividade, AtividadeDTO> {
}

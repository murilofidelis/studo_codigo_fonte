package br.com.studo.domain.mapper;

import br.com.studo.domain.Matricula;
import br.com.studo.domain.dto.MatriculaDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {
        AlunoMapper.class,
        TurmaMapper.class
})
public interface MatriculaMapper extends AbstractMapper<Matricula, MatriculaDTO> {
}

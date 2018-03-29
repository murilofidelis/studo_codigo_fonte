package br.com.studo.domain.mapper;

import br.com.studo.domain.Professor;
import br.com.studo.domain.dto.ProfessorDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {
        EmailMapper.class,
        EnderecoMapper.class
})
public interface ProfessorMapper extends AbstractMapper<Professor, ProfessorDTO> {
}

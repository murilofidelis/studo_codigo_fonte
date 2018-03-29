package br.com.studo.domain.mapper;

import br.com.studo.domain.Aluno;
import br.com.studo.domain.dto.AlunoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {
        EmailMapper.class
})
public interface AlunoMapper extends AbstractMapper<Aluno, AlunoDTO> {
}

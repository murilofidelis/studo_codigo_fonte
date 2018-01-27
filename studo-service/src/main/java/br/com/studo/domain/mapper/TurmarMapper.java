package br.com.studo.domain.mapper;

import br.com.studo.domain.Turma;
import br.com.studo.domain.dto.TurmaDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper
public interface TurmarMapper {
    public Turma toTurma(TurmaDTO turmaDTO);

    @InheritInverseConfiguration
    public TurmaDTO fromTurma(Turma turma);

}

package br.com.studo.domain.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.None.class, scope = DisciplinaDTO.class)
public class DisciplinaDTO {

    private Long codigo;

    private String descricao;

    private Boolean ativa;
}

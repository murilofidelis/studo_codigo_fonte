package br.com.studo.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DisciplinaDTO {

    private Long codigo;
    private String descricao;
    private Boolean ativa;
}

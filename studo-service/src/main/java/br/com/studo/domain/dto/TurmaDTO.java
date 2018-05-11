package br.com.studo.domain.dto;

import br.com.studo.domain.enums.Periodo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TurmaDTO {

    private Long codigo;

    private String numeroTurma;

    private String serie;

    private String descricaoTurma;

    private String sala;

    private Periodo periodo;

    private Integer ano;

}

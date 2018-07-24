package br.com.studo.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class DashbordDTO implements Serializable {

    private Integer numAlunos;

    private Integer numDisciplinas;

    private Integer numProfessores;

    private Integer numTurmas;

}

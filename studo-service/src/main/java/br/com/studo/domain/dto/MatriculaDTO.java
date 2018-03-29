package br.com.studo.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MatriculaDTO {

    private Long codigo;

    private String matricula;

    private AlunoDTO alunoDTO;

    private TurmaDTO turmaDTO;

    private LocalDate dataMatricula;

    private Boolean turmaAtual;
}

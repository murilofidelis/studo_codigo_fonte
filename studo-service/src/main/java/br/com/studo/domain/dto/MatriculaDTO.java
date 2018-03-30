package br.com.studo.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MatriculaDTO {

    private Long codigo;

    private String matricula;

    private AlunoDTO aluno;

    private TurmaDTO turma;

    private LocalDate dataMatricula;

    private Boolean turmaAtual;
}

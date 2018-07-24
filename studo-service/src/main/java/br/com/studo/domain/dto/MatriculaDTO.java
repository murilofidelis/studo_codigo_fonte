package br.com.studo.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class MatriculaDTO implements Serializable {

    private Long codigo;

    private String numMatricula;

    private AlunoDTO aluno;

    private TurmaDTO turma;

    private LocalDate dataMatricula;

    private Boolean turmaAtual;
}

package br.com.studo.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class AtividadeDTO implements Serializable {

    private Long codigo;

    private LocalDate dataCadastro;

    private String titulo;

    private String descricao;

    private String classificacao;

    private ProfessorDTO professor;

    private DisciplinaDTO disciplina;
}

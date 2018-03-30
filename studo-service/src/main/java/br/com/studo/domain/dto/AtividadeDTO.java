package br.com.studo.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AtividadeDTO {

    private Long codigo;

    private LocalDateTime dataCadastro;

    private String titulo;

    private String descricao;

    private String classificacao;

    private ProfessorDTO professor;

    private DisciplinaDTO disciplina;
}

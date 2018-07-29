package br.com.studo.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlunoRelatorioDTO {

    private Long codigo;

    private String nome;

    private String cpf;

    private String sexo;

    private String status;

    private String dataNascimento;

    private String email;
}

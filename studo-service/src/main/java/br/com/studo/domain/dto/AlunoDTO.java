package br.com.studo.domain.dto;

import br.com.studo.domain.enums.Sexo;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class AlunoDTO implements Serializable {

    private Long codigo;

    private String nome;

    private String cpf;

    private Sexo sexo;

    private Boolean status;

    private LocalDate dataNascimento;

    private EmailDTO email;
}

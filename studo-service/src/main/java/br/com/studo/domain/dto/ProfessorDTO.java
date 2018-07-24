package br.com.studo.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ProfessorDTO implements Serializable {

    private Long codigo;

    private String nome;

    private String cpf;

    private String sexo;

    private Boolean status;

    private EmailDTO email;

    private EnderecoDTO endereco;
}

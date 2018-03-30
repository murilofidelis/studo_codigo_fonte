package br.com.studo.domain.dto;

import br.com.studo.domain.Email;
import br.com.studo.domain.Endereco;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfessorDTO {

    private Long codigo;

    private String nome;

    private String cpf;

    private String sexo;

    private Boolean status;

    private EmailDTO email;

    private EnderecoDTO endereco;
}

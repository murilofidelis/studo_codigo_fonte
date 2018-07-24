package br.com.studo.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class EnderecoDTO implements Serializable {

    private Long codigo;

    private String cep;

    private String uf;

    private String localidade;

    private String logradouro;

    private String bairro;

    private String numero;

    private String complemento;
}

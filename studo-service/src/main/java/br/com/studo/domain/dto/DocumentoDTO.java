package br.com.studo.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class DocumentoDTO implements Serializable {

    private Long codigo;

    private Long codigoAluno;

    private String nome;

    private String extensao;

    private String descricao;

    private String tipo;

    private String caminho;

    private String anexoBase64;
}

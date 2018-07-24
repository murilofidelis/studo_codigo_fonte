package br.com.studo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class DocumentoDTO implements Serializable {

    private Long codigo;

    private String nome;
}

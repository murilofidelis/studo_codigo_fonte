package br.com.studo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DocumentoDTO {

    private Long codigo;

    private String nome;
}

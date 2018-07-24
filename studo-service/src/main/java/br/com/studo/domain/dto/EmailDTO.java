package br.com.studo.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class EmailDTO implements Serializable {

    private Long codigo;

    private String dscEmail;
}

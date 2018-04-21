package br.com.studo.domain.enums;

import lombok.Getter;

@Getter
public enum ClassificacaoTurma {

    QUINTA_SERIE(5L, "Quinta Série"),
    SEXTA_SERIE(6L, "Sexta Série"),
    SETIMA_SERIE(7L, "Setima Seríe");

    private Long codigo;
    private String descricao;

    ClassificacaoTurma(Long codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

}

package br.com.studo.domain.enums;

import lombok.Getter;

@Getter
public enum ClassificacaoTurma {

    QUINTA_SERIE(5L, "Quinta Série"),
    SEXTA_SERIE(6L, "Sexta Série"),
    SETIMA_SERIE(7L, "Setima Seríe"),
    OITAVA_SERIE(8L, "Oitava Seríe"),
    NONA_SERIE(9L, "Nona Seríe"),
    PRIMEIRO_ANO(10L, "Primeiro Ano"),
    SEGUNDO_ANO(11L, "Segundo Ano"),
    TERCEIRO_ANO(12L, "Terceiro Ano");

    private Long codigo;
    private String descricao;

    ClassificacaoTurma(Long codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

}

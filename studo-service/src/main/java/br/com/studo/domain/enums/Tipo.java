package br.com.studo.domain.enums;

public enum Tipo {

    SECRETARIO("Secretário", 1L),
    PROFESSOR("Professor", 2L),
    ALUNO("Aluno", 3L);

    private String descricao;
    private Long codigo;

    Tipo(String descricao, long codigo) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Long getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }
}

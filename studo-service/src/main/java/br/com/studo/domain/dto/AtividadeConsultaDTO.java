package br.com.studo.domain.dto;

import br.com.studo.annotation.consultanativa.ConsultaNativaColuna;
import br.com.studo.annotation.consultanativa.ConsultaNativaResultado;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@ConsultaNativaResultado
public class AtividadeConsultaDTO {

    @ConsultaNativaColuna(indice = 0)
    private Long codigo;

    @ConsultaNativaColuna(indice = 1)
    private String classificacao;

    @ConsultaNativaColuna(indice = 2)
    private Date dataCadastro;

    @ConsultaNativaColuna(indice = 3)
    private String descricao;

    @ConsultaNativaColuna(indice = 4)
    private String titulo;

    @ConsultaNativaColuna(indice = 5)
    private String disciplina;
}

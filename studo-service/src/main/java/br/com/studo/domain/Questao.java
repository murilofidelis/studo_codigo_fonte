package br.com.studo.domain;

import br.com.studo.domain.enuns.Alternativa;
import br.com.studo.domain.enuns.TipoQuestao;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "tab_questoes")
public class Questao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @NotNull
    @OneToOne
    private Atividade atividade;

    @NotNull
    private Integer numero;

    @NotNull
    private String descricao;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoQuestao tipoQuestao;

    @NotNull
    private Alternativa alternativaCorreta;

    private String alternativaA;

    private String alternativaB;

    private String alternativaC;

    private String alternativaD;

    private String alternativaE;
}

package br.com.studo.domain;

import br.com.studo.domain.enuns.Alternativa;
import br.com.studo.domain.enuns.TipoQuestao;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "tab_questoes")
public class Questao implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private Long codigo;

    @OneToOne
    private Atividade atividade;

    @NotNull
    @Size(max = 5)
    @Column(name = "numero_questao")
    private Integer numero;

    @NotNull
    @Size(max = 2048)
    @Column(name = "descricao")
    private String descricao;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "tipoQuestao")
    private TipoQuestao tipoQuestao;

    @NotNull
    @Size(max = 1)
    @Column(name = "alternativaCorreta")
    private Alternativa alternativaCorreta;

    @Column(name = "alternativa_A")
    @Size(max = 250)
    private String alternativaA;

    @Column(name = "alternativa_B")
    @Size(max = 250)
    private String alternativaB;

    @Column(name = "alternativa_C")
    @Size(max = 250)
    private String alternativaC;

    @Column(name = "alternativa_D")
    @Size(max = 250)
    private String alternativaD;

    @Column(name = "alternativa_E")
    @Size(max = 250)
    private String alternativaE;
}

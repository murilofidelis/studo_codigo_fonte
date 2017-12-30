package br.com.studo.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class PessoaDisciplinaId implements Serializable {

    @ManyToOne
    private Pessoa pessoa;

    @ManyToOne
    private Disciplina disciplina;
}

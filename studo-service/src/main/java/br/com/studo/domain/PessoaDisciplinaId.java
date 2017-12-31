package br.com.studo.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class PessoaDisciplinaId implements Serializable {

    private static final long serialVersionUID = -7342796447755202077L;

    @ManyToOne
    private Pessoa pessoa;

    @ManyToOne
    private Disciplina disciplina;
}

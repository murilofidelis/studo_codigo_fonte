package br.com.studo.domain;

import br.com.studo.util.DateConverter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "tab_matricula")
public class Matricula implements Serializable {

    private static final long serialVersionUID = -927136295454282048L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private Long codigo;

    @NotNull
    @Column(name = "matricula", unique = true)
    private Long matricula;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "turma_id")
    private Turma turma;

    @NotNull
    @Convert(converter = DateConverter.class)
    @Column(name = "dte_cadastro")
    private LocalDate dataCadastro;

    @NotNull
    @Column(name = "bln_turma_atual")
    private Boolean turmaAtual;
}

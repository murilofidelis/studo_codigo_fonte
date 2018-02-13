package br.com.studo.domain;

import br.com.studo.util.DateConverter;
import br.com.studo.domain.enuns.Sexo;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "tab_aluno")
public class Aluno implements Serializable {

    private static final long serialVersionUID = 5752740166747344166L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private Long codigo;

    @NotNull
    @Column(name = "nome")
    @Size(max = 50)
    private String nome;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "sexo")
    private Sexo sexo;

    @NotNull
    @Column(name = "bln_status")
    private Boolean status;

    @NotNull
    @Convert(converter = DateConverter.class)
    @Column(name = "dte_nascimento")
    private LocalDate dataNascimento;

    @NotNull
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "codigo_email")
    private Email email;

}

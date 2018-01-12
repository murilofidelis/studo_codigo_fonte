package br.com.studo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "tab_atividade")
public class Atividade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @NotNull
    private LocalDate dataCadastro;

    @NotNull
    @Size(max = 250)
    private String titulo;

    @NotNull
    @Size(max = 1024)
    private String descricao;

    private String classificacao;

    @OneToOne
    private Pessoa pessoa;

    @OneToOne
    private Disciplina disciplina;
}

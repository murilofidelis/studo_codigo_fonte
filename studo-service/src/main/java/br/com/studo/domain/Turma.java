package br.com.studo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "tab_turma")
public class Turma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @NotNull
    @Size(max = 30)
    private String serie;

    @NotNull
    @Size(max = 1)
    private String descricaoTurma;

    @NotNull
    @Size(max = 4)
    private String sala;

    @NotNull
    @Size(max = 20)
    private String periodo;

    @NotNull
    @Size(max = 4)
    private Integer ano;
}

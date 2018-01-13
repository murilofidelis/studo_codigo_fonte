package br.com.studo.domain;

import br.com.studo.domain.enuns.Periodo;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tab_turma")
public class Turma implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private Long codigo;

    @NotNull
    @Size(max = 30)
    @Column(name = "serie")
    private String serie;

    @NotNull
    @Size(max = 1)
    @Column(name = "dsc_turma")
    private String descricaoTurma;

    @NotNull
    @Size(max = 4)
    @Column(name = "sala")
    private String sala;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "periodo")
    private Periodo periodo;

    @NotNull
    @Column(name = "ano")
    private Integer ano;

    @OneToMany(mappedBy = "turma", cascade = CascadeType.ALL)
    private List<TurmaAluno> turmaAlunos;
}

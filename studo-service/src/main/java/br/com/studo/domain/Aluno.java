package br.com.studo.domain;

import br.com.studo.domain.enuns.Sexo;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tab_aluno")
public class Aluno implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    private String nome;

    private String email;

    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    private Boolean status;

    private LocalDate dataNascimento;

    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL)
    private List<TurmaAluno> turmaAlunos;

    @OneToOne
    private Usuario usuario;
}

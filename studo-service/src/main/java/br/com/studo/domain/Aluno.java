package br.com.studo.domain;

import br.com.studo.domain.enuns.Sexo;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
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
    @Column(name = "codigo")
    private Long codigo;

    @Column(name = "nome")
    @Size(max = 50)
    private String nome;

    @Column(name = "email")
    @Size(max = 50)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "sexo")
    private Sexo sexo;

    @Column(name = "bln_status")
    private Boolean status;

    @Column(name = "dte_nascimento")
    private LocalDate dataNascimento;

    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL)
    private List<TurmaAluno> turmaAlunos;

    @OneToOne
    private Usuario usuario;
}

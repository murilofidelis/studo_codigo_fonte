package br.com.studo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tab_disciplina")
public class Disciplina implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private Long codigo;

    @NotNull
    @Size(max = 50)
    @Column(name = "descricao")
    private String descricao;

    @OneToMany(mappedBy = "disciplina", cascade = CascadeType.ALL)
    private List<PessoaDisciplina> pessoaDisciplinas;

    @NotNull
    @Column(name = "bln_ativa")
    private Boolean ativa;

}

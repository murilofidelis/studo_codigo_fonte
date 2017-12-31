package br.com.studo.domain;

import br.com.studo.domain.enuns.Tipo;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tab_pessoa")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codPessoa;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    @NotNull
    @Size(max = 50)
    private String nome;

    @NotNull
    @Size(max = 11)
    private String cpf;

    @NotNull
    private Boolean situacao;

    @NotNull
    private String sexo;

    @OneToMany
    @JoinColumn(name = "cod_pessoa")
    private List<Email> emais;

    @OneToOne
    private Endereco endereco;

    @OneToMany(mappedBy = "id.pessoa")
    private List<PessoaDisciplina> pessoaDisciplinas;

    @OneToOne
    private Usuario usuario;

}

package br.com.studo.domain;

import br.com.studo.domain.enuns.Tipo;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tab_pessoa")
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1358987494816807819L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private Long codigo;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_pessoa")
    private Tipo tipo;

    @NotNull
    @Size(max = 50)
    @Column(name = "nome")
    private String nome;

    @NotNull
    @Size(max = 11)
    @Column(name = "cpf")
    private String cpf;

    @NotNull
    @Column(name = "sexo")
    private String sexo;

    @OneToMany
    @JoinColumn(name = "codigo_pessoa")
    private List<Email> emais;

    @OneToOne
    private Endereco endereco;

    @OneToOne
    private Usuario usuario;

}

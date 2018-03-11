package br.com.studo.domain;

import br.com.studo.domain.enums.Tipo;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "tab_usuario", schema = "usuarios")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 8024177994467350678L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private Long codigo;

    @NotNull
    @Size(max = 50)
    @Column(name = "login", unique = true)
    private String login;

    @NotNull
    @Size(max = 255)
    @Column(name = "senha")
    private String senha;

    @NotNull
    @Size(max = 50)
    @Column(name = "nome")
    private String nome;

    @NotNull
    @Size(max = 50)
    @Column(name = "email")
    private String email;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_usuario")
    private Tipo tipo;

    @Column(name = "bln_status")
    private Boolean status;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tab_usuario_permissao", schema = "usuarios",
            joinColumns = @JoinColumn(name = "codigo_usuario", referencedColumnName = "codigo"),
            inverseJoinColumns = @JoinColumn(name = "codigo_permissao", referencedColumnName = "codigo"))
    private Set<Permissao> permissoes;

}

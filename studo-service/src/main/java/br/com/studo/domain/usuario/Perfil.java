package br.com.studo.domain.usuario;

import br.com.studo.domain.enums.TipoPerfil;
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
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "tab_perfil", schema = "usuarios")
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private Long codigo;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "dsc_perfil")
    private TipoPerfil tipoPerfilUsuario;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tab_perfil_permissao", schema = "usuarios",
            joinColumns = @JoinColumn(name = "codigo_perfil", referencedColumnName = "codigo"),
            inverseJoinColumns = @JoinColumn(name = "codigo_permissao", referencedColumnName = "codigo"))
    private Set<Permissao> permissoes;

}

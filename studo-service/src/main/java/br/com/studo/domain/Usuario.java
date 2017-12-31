package br.com.studo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "tab_usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codUsuario;

    @NotNull
    @Size(max = 20)
    @Column(unique = true)
    private String usuario;

    @NotNull
    @Size(max = 10)
    private String senha;
}

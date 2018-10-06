package br.com.studo.domain;

import br.com.studo.domain.enums.Periodo;
import lombok.Getter;
import lombok.Setter;
import org.javers.core.metamodel.annotation.TypeName;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@Entity
@TypeName("tab_turma")
@Table(name = "tab_turma",  schema = "studo")
public class Turma implements Serializable {

    private static final long serialVersionUID = 6109374665565097280L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private Long codigo;

    @NotNull
    @Column(name = "numero_turma", unique = true)
    private String numeroTurma;

    @NotNull
    @Size(max = 30)
    @Column(name = "serie")
    private String serie;

    @NotNull
    @Size(max = 1)
    @Column(name = "dsc_turma")
    private String descricaoTurma;

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

}

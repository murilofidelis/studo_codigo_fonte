package br.com.studo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tab_documentos", schema = "studo")
public class Documento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private Long codigo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codigo_aluno", foreignKey = @ForeignKey(name = "fk_aluno_documento"))
    private Aluno aluno;

    @NotNull
    @Column(name = "nome_documento")
    private String nome;

    @NotNull
    @Column(name = "extensao")
    private String extensao;

    @NotNull
    @Column(name = "descricao")
    private String descricao;

    @NotNull
    @Column(name = "tipo")
    private String tipo;

    @NotNull
    @Column(name = "caminho")
    private String caminho;
}

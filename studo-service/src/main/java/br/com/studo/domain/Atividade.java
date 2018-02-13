package br.com.studo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "tab_atividade")
public class Atividade implements Serializable {

	private static final long serialVersionUID = -1847561676474026120L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo")
	private Long codigo;

	@NotNull
	@Column(name = "dte_cadastro")
	LocalDateTime dataCadastro;

	@NotNull
	@Size(max = 250)
	@Column(name = "titulo")
	String titulo;

	@NotNull
	@Size(max = 1024)
	@Column(name = "descricao")
	String descricao;

	@Column(name = "dsc_classificacao")
	@Size(max = 30)
	private String classificacao;

	@OneToOne
	private Professor professor;

	@OneToOne
	private Disciplina disciplina;
}

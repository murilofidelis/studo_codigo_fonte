package br.com.studo.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.studo.domain.enuns.Tipo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tab_pessoa")
public class Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cod_pessoa;

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

	@ManyToMany
	@JoinTable(name = "pessoa_disciplina")
	private List<Disciplina> disciplinas;

	@NotNull
	@Size(max = 10)
	private String senha;

}

package br.com.studo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tab_endereco")
public class Endereco {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codEndereco;

	@NotNull
	private String cep;

	@NotNull
	private String cidade;

	@NotNull
	private String estado;

	@NotNull
	private String logradouro;

	@NotNull
	private String numero;

	@NotNull
	private String bairro;

	@Size(max = 100)
	private String complemento;

}

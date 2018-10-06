package br.com.studo.domain;

import lombok.Getter;
import lombok.Setter;
import org.javers.core.metamodel.annotation.TypeName;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@Entity
@TypeName("tab_disciplina")
@Table(name = "tab_disciplina",  schema = "studo")
public class Disciplina implements Serializable {

	private static final long serialVersionUID = 124926647057211327L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo")
	private Long codigo;

	@NotNull
	@Size(max = 50)
	@Column(name = "descricao")
	private String descricao;

	@NotNull
	@Column(name = "bln_ativa")
	private Boolean ativa;

}

package br.com.studo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import org.javers.core.metamodel.annotation.TypeName;

import java.io.Serializable;

@Getter
@Setter
@Entity
@TypeName("tab_email")
@Table(name = "tab_email",  schema = "studo")
public class Email implements Serializable {

	private static final long serialVersionUID = -2001171565703568324L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo")
	private Long codigo;

	@Size(max = 50)
	@Column(name = "dsc_email")
	private String dscEmail;

}

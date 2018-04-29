package br.com.studo.domain;


import br.com.studo.util.DateTimeConverter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "tab_log_erro", schema = "studo")
public class LogErro implements Serializable {

    private static final long serialVersionUID = 7307837611732830266L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @Column(name = "dte_erro")
    @Convert(converter = DateTimeConverter.class)
    private LocalDateTime data;

    @Column(name = "usuario_logado")
    @Size(max = 30)
    private String usuarioLogado;

    @Column(name = "log_erro")
    @Size(max = 30000)
    private String stackTrace;

}

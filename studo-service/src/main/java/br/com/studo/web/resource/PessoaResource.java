package br.com.studo.web.resource;

import br.com.studo.domain.Professor;
import br.com.studo.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("pessoas")
public class PessoaResource {

    @Autowired
    private PessoaService pessoaService;

    @PostMapping
    public ResponseEntity<Professor> salvar(@RequestBody @Valid Professor pessoa) {
        Professor pessoaSalva = pessoaService.salvar(pessoa);
        return pessoaSalva != null ? ResponseEntity.status(HttpStatus.CREATED).build() : ResponseEntity.badRequest().build();
    }

}

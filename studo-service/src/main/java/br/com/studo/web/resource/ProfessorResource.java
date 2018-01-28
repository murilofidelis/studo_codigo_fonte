package br.com.studo.web.resource;

import br.com.studo.domain.Professor;
import br.com.studo.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("professor")
public class ProfessorResource {

    @Autowired
    private ProfessorService pessoaService;

    @PostMapping
    public ResponseEntity<Professor> salvar(@RequestBody @Valid Professor professor) {
        Professor pessoaSalva = pessoaService.salvar(professor);
        return pessoaSalva != null ? ResponseEntity.status(HttpStatus.CREATED).build() : ResponseEntity.badRequest().build();
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Boolean> verificaCpfCadastrado(@PathVariable String cpf) {
        return ResponseEntity.ok().body(pessoaService.verificaCpfCadastrado(cpf));
    }

}

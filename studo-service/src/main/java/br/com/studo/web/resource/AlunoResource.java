package br.com.studo.web.resource;

import br.com.studo.domain.Aluno;
import br.com.studo.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("aluno")
public class AlunoResource {

    @Autowired
    private AlunoService alunoService;

    @PostMapping
    public ResponseEntity<Aluno> salvarAluno(@RequestBody Aluno aluno) {
        Aluno alunoSalvo = alunoService.salvarAluno(aluno);
        return alunoSalvo != null ? ResponseEntity.status(HttpStatus.CREATED).build() : ResponseEntity.badRequest().build();
    }


}
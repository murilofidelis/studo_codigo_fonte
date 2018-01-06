package br.com.studo.web.resource;

import br.com.studo.domain.Turma;
import br.com.studo.service.TurmaService;
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
@RequestMapping("turmas")
public class TurmaResource {

    @Autowired
    private TurmaService turmaService;

    @PostMapping
    public ResponseEntity<Turma> salvar(@RequestBody @Valid Turma turma) {
        Turma turmaSalva = turmaService.salvar(turma);
        return turmaSalva != null ? ResponseEntity.status(HttpStatus.CREATED).build() : ResponseEntity.badRequest().build();
    }
}

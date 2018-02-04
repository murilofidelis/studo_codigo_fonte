package br.com.studo.web.resource;

import br.com.studo.domain.Turma;
import br.com.studo.domain.enuns.Periodo;
import br.com.studo.service.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("turmas")
public class TurmaResource {

    @Autowired
    private TurmaService turmaService;

    @GetMapping
    public Page<Turma> filtarPesquisa(
            @RequestParam(required = false, defaultValue = "MATUTINO, VESPERTINO, NOTURNO") List<Periodo> periodo,
            @RequestParam(required = false, defaultValue = "2018") Integer ano,
            Pageable pageable) {
        return turmaService.filtarPesquisa(periodo, ano, pageable);
    }

    @PostMapping
    public ResponseEntity<Turma> salvar(@RequestBody Turma turma) {
        Turma turmaSalva = turmaService.salvar(turma);
        return turmaSalva != null ? ResponseEntity.status(HttpStatus.CREATED).build() : ResponseEntity.badRequest().build();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Turma> buscarPorCodigo(@PathVariable Long codigo) {
        Turma turma = turmaService.buscarPorCodigo(codigo);
        return turma != null ? ResponseEntity.ok().body(turma) : ResponseEntity.notFound().build();
    }

    @GetMapping("/buscaPorNumero")
    public ResponseEntity<Turma> buscarPorNumero(@RequestParam String numTurma) {
        Turma turma = turmaService.buscaTurmaPorNumero(numTurma);
        return turma != null ? ResponseEntity.ok().body(turma) : ResponseEntity.notFound().build();
    }
}

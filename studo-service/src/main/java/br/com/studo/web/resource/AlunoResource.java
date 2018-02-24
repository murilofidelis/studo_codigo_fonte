package br.com.studo.web.resource;

import br.com.studo.domain.Aluno;
import br.com.studo.domain.Matricula;
import br.com.studo.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("alunos")
public class AlunoResource {

    @Autowired
    private AlunoService alunoService;

    @GetMapping
    public Page<Aluno> filtrarPesquisa(@RequestParam(required = false, defaultValue = "%") String nome, Pageable pageable) {
        return alunoService.filtarPesquisar(nome, pageable);
    }

    @PostMapping
    public ResponseEntity salvarAluno(@RequestBody Aluno aluno) {
        Aluno alunoSalvo = alunoService.salvarAluno(aluno);
        return alunoSalvo != null ? ResponseEntity.status(HttpStatus.CREATED).build() : ResponseEntity.badRequest().build();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Aluno> buscaPorCodigo(@PathVariable Long codigo) {
        Aluno aluno = alunoService.buscaPorCodigo(codigo);
        return aluno != null ? ResponseEntity.ok().body(aluno) : ResponseEntity.notFound().build();
    }

    @PostMapping("/matricula")
    public ResponseEntity salvaMatricula(@RequestBody Matricula matricula) {
        Matricula matriculaSalva = alunoService.salvaMatricula(matricula);
        return matriculaSalva != null ? ResponseEntity.status(HttpStatus.CREATED).build() : ResponseEntity.badRequest().build();
    }

    @GetMapping("/matriculas/{codigo}")
    public ResponseEntity buscaMatriculasPorAluno(@PathVariable Long codigo) {
        return ResponseEntity.ok().body(alunoService.buscaMatriculasPorAluno(codigo));
    }

    @DeleteMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletaMatriculaAluno(@PathVariable Long codMatricula) {
        alunoService.deletaMatriculaAluno(codMatricula);
    }
}
package br.com.studo.web.resource;

import br.com.studo.domain.Aluno;
import br.com.studo.domain.Matricula;
import br.com.studo.service.AlunoService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAuthority('ROLE_LISTAR_ALUNO')")
    public Page<Aluno> filtrarPesquisa(@RequestParam(required = false, defaultValue = "%") String nome, Pageable pageable) {
        return alunoService.filtarPesquisar(nome, pageable);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_ALUNO')")
    public ResponseEntity<Aluno> salvarAluno(@RequestBody Aluno aluno) {
        Aluno alunoSalvo = alunoService.salvarAluno(aluno);
        return alunoSalvo != null ? ResponseEntity.status(HttpStatus.CREATED).build() : ResponseEntity.badRequest().build();
    }

    @GetMapping("/verifica/{cpf}")
    @PreAuthorize("hasAuthority('ROLE_LISTAR_ALUNO')")
    public ResponseEntity<Boolean> verificaCpfCadastrado(@PathVariable String cpf) {
        return ResponseEntity.ok().body(alunoService.verificaCpfCadastrado(cpf));
    }

    @GetMapping("/{codigo}")
    @PreAuthorize("hasAuthority('ROLE_LISTAR_ALUNO')")
    public ResponseEntity<Aluno> buscaPorCodigo(@PathVariable Long codigo) {
        Aluno aluno = alunoService.buscaPorCodigo(codigo);
        return aluno != null ? ResponseEntity.ok().body(aluno) : ResponseEntity.notFound().build();
    }

    @PostMapping("/matricula")
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_ALUNO')")
    public ResponseEntity<Aluno> salvaMatricula(@RequestBody Matricula matricula) {
        Matricula matriculaSalva = alunoService.salvaMatricula(matricula);
        return matriculaSalva != null ? ResponseEntity.status(HttpStatus.CREATED).build() : ResponseEntity.badRequest().build();
    }

    @GetMapping("/matriculas/{codigo}")
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_ALUNO')")
    public ResponseEntity<List<Matricula>> buscaMatriculasPorAluno(@PathVariable Long codigo) {
        return ResponseEntity.ok().body(alunoService.buscaMatriculasPorAluno(codigo));
    }

    @DeleteMapping("/{codMatricula}")
    @PreAuthorize("hasAuthority('ROLE_ALTERAR_ALUNO')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletaMatriculaAluno(@PathVariable Long codMatricula) {
        alunoService.deletaMatriculaAluno(codMatricula);
    }
}
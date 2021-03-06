package br.com.studo.web.resource;

import br.com.studo.domain.Aluno;
import br.com.studo.domain.dto.AlunoDTO;
import br.com.studo.domain.dto.MatriculaDTO;
import br.com.studo.service.AlunoService;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("alunos")
public class AlunoResource {

    @Autowired
    private AlunoService alunoService;

    @GetMapping("/filtrar")
    @PreAuthorize("hasAuthority('ROLE_LISTAR_ALUNO')")
    public Page<Aluno> filtar(@QuerydslPredicate(root = Aluno.class) Predicate predicate,
                                      @RequestParam("page") int page,
                                      @RequestParam("size") int size,
                                      @RequestParam("sortField") String sortField,
                                      @RequestParam("sortOrder") String sortOrder) {
        return alunoService.filtrar(predicate, new PageRequest(page, size, new Sort(new Sort.Order(Sort.Direction.fromString(sortOrder), sortField))));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_LISTAR_ALUNO')")
    public Page<Aluno> filtrarPesquisa(@RequestParam(required = false, defaultValue = "%") String nome, Pageable pageable) {
        return alunoService.filtarPesquisar(nome, pageable);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_ALUNO')")
    public ResponseEntity<AlunoDTO> salvarAluno(@RequestBody AlunoDTO alunoDTO) {
        AlunoDTO alunoSalvo = alunoService.salvarAluno(alunoDTO);
        return alunoSalvo != null ? ResponseEntity.status(HttpStatus.CREATED).build() : ResponseEntity.badRequest().build();
    }

    @GetMapping("/verifica/{cpf}")
    @PreAuthorize("hasAuthority('ROLE_LISTAR_ALUNO')")
    public ResponseEntity<Boolean> verificaCpfCadastrado(@PathVariable String cpf) {
        return ResponseEntity.ok().body(alunoService.verificaCpfCadastrado(cpf));
    }

    @GetMapping("/{codigo}")
    @PreAuthorize("hasAuthority('ROLE_LISTAR_ALUNO')")
    public ResponseEntity<AlunoDTO> buscaPorCodigo(@PathVariable Long codigo) {
        AlunoDTO alunoDTO = alunoService.buscaPorCodigo(codigo);
        return alunoDTO != null ? ResponseEntity.ok().body(alunoDTO) : ResponseEntity.notFound().build();
    }

    @PostMapping("/matricula")
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_ALUNO')")
    public ResponseEntity<HttpStatus> salvaMatricula(@RequestBody MatriculaDTO matriculaDTO) {
        MatriculaDTO matriculaSalva = alunoService.salvaMatricula(matriculaDTO);
        return matriculaSalva != null ? ResponseEntity.status(HttpStatus.CREATED).build() : ResponseEntity.badRequest().build();
    }

    @GetMapping("/matriculas/{codigo}")
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_ALUNO')")
    public ResponseEntity<List<MatriculaDTO>> buscaMatriculasPorAluno(@PathVariable Long codigo) {
        return ResponseEntity.ok().body(alunoService.buscaMatriculasPorAluno(codigo));
    }

    @DeleteMapping("/{codMatricula}")
    @PreAuthorize("hasAuthority('ROLE_ALTERAR_ALUNO')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletaMatriculaAluno(@PathVariable Long codMatricula) {
        alunoService.deletaMatriculaAluno(codMatricula);
    }

    @ResponseBody
    @GetMapping("/geraRelatorioAlunos")
    public void geraPDF(HttpServletResponse response) {
        alunoService.geraRelatorioAluno(response);
    }

}
package br.com.studo.web.resource;

import br.com.studo.domain.Professor;
import br.com.studo.domain.dto.ProfessorDTO;
import br.com.studo.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("professor")
public class ProfessorResource {

    @Autowired
    private ProfessorService professorService;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_LISTAR_PROFESSOR')")
    public Page<Professor> filtarPesquisa(@RequestParam(required = false, defaultValue = "%") String nome, Pageable pageable) {
        return professorService.filtarPesquisar(nome, pageable);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_PROFESSOR')")
    public ResponseEntity<ProfessorDTO> salvar(@RequestBody @Valid ProfessorDTO professorDTO) {
        ProfessorDTO pessoaSalva = professorService.salvar(professorDTO);
        return pessoaSalva != null ? ResponseEntity.status(HttpStatus.CREATED).build() : ResponseEntity.badRequest().build();
    }

    @GetMapping("/verifica/{cpf}")
    @PreAuthorize("hasAuthority('ROLE_LISTAR_PROFESSOR')")
    public ResponseEntity<Boolean> verificaCpfCadastrado(@PathVariable String cpf) {
        return ResponseEntity.ok().body(professorService.verificaCpfCadastrado(cpf));
    }

    @GetMapping("/{codigo}")
    @PreAuthorize("hasAuthority('ROLE_LISTAR_PROFESSOR')")
    public ResponseEntity<ProfessorDTO> buscaPorCodigo(@PathVariable Long codigo) {
        ProfessorDTO professor = professorService.buscaPorCodigo(codigo);
        return professor != null ? ResponseEntity.ok().body(professor) : ResponseEntity.notFound().build();
    }

}

package br.com.studo.web.resource;

import br.com.studo.domain.Turma;
import br.com.studo.domain.dto.TurmaDTO;
import br.com.studo.domain.enums.Periodo;
import br.com.studo.service.TurmaService;
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

import java.util.List;

@RestController
@RequestMapping("turmas")
public class TurmaResource {

    @Autowired
    private TurmaService turmaService;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_LISTAR_TURMA')")
    public Page<Turma> filtarPesquisa(
            @RequestParam(required = false, defaultValue = "MATUTINO, VESPERTINO, NOTURNO") List<Periodo> periodo,
            @RequestParam(required = false, defaultValue = "2018") Integer ano,
            Pageable pageable) {
        return turmaService.filtarPesquisa(periodo, ano, pageable);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_TURMA')")
    public ResponseEntity<TurmaDTO> salvar(@RequestBody TurmaDTO turmaDTO) {
        TurmaDTO turmaSalva = turmaService.salvar(turmaDTO);
        return turmaSalva != null ? ResponseEntity.status(HttpStatus.CREATED).build() : ResponseEntity.badRequest().build();
    }

    @GetMapping("/{codigo}")
    @PreAuthorize("hasAuthority('ROLE_LISTAR_TURMA')")
    public ResponseEntity<TurmaDTO> buscarPorCodigo(@PathVariable Long codigo) {
        TurmaDTO turma = turmaService.buscarPorCodigo(codigo);
        return turma != null ? ResponseEntity.ok().body(turma) : ResponseEntity.notFound().build();
    }

    @GetMapping("/buscaPorNumero")
    @PreAuthorize("hasAuthority('ROLE_LISTAR_TURMA')")
    public ResponseEntity<TurmaDTO> buscarPorNumero(@RequestParam String numTurma) {
        TurmaDTO turmaDTO = turmaService.buscaTurmaPorNumero(numTurma);
        return turmaDTO != null ? ResponseEntity.ok().body(turmaDTO) : ResponseEntity.notFound().build();
    }
}

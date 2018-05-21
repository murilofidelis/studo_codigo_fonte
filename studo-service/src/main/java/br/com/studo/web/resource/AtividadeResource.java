package br.com.studo.web.resource;

import br.com.studo.domain.dto.AtividadeDTO;
import br.com.studo.domain.enums.ClassificacaoTurma;
import br.com.studo.repository.AtividadeRepository;
import br.com.studo.service.AtividadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("atividade")
public class AtividadeResource {

    @Autowired
    private AtividadeService atividadeService;

    @Autowired
    private AtividadeRepository repository;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_LISTAR_ATIVIDADE')")
    public Page<AtividadeDTO> buscaAtividades(@RequestParam MultiValueMap<String, String> parametros, Pageable pageable) {
        return atividadeService.buscaAtividades(parametros, pageable);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_ATIVIDADE')")
    public ResponseEntity salvar(@RequestBody AtividadeDTO atividadeDTO) {
        AtividadeDTO atividade = atividadeService.salvar(atividadeDTO);
        return atividade != null ? ResponseEntity.status(HttpStatus.CREATED).build() : ResponseEntity.badRequest().build();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<AtividadeDTO> buscaPoroCodigo(@PathVariable Long codigo) {
        AtividadeDTO atividadeDTO = atividadeService.buscaPorCodigo(codigo);
        return atividadeDTO != null ? ResponseEntity.status(HttpStatus.OK).body(atividadeDTO) : ResponseEntity.notFound().build();
    }

    @GetMapping("/listaClassificacao")
    public ResponseEntity<List<ClassificacaoTurma>> listaClassificacoes() {
        return ResponseEntity.ok().body(atividadeService.listaClassificao());
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long codigo) {
        atividadeService.excluir(codigo);
    }
}

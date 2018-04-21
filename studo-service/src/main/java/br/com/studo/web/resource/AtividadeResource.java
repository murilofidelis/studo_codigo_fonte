package br.com.studo.web.resource;

import br.com.studo.domain.dto.AtividadeDTO;
import br.com.studo.domain.enums.ClassificacaoTurma;
import br.com.studo.service.AtividadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("atividade")
public class AtividadeResource {

    @Autowired
    private AtividadeService atividadeService;

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_ATIVIDADE')")
    public ResponseEntity salvar(@RequestBody AtividadeDTO atividadeDTO) {
        AtividadeDTO atividade = atividadeService.salvar(atividadeDTO);
        return atividade != null ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

    @GetMapping("/listaClassificacao")
    public ResponseEntity<List<ClassificacaoTurma>> listaClassificacoes() {
        return ResponseEntity.ok().body(atividadeService.listaClassificao());
    }
}

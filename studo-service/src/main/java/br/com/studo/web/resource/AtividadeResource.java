package br.com.studo.web.resource;

import br.com.studo.domain.dto.AtividadeDTO;
import br.com.studo.service.AtividadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("atividade")
public class AtividadeResource {

    @Autowired
    private AtividadeService atividadeService;

    @PostMapping
    public ResponseEntity salvar(AtividadeDTO atividadeDTO) {
        AtividadeDTO atividade = atividadeService.salvar(atividadeDTO);
        return atividade != null ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }
}

package br.com.studo.service;

import br.com.studo.domain.dto.AtividadeDTO;
import br.com.studo.domain.enums.ClassificacaoTurma;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.MultiValueMap;

import java.util.List;

public interface AtividadeService {

    Page<AtividadeDTO> buscaAtividades(MultiValueMap<String, String> parametros, Pageable pageable);

    AtividadeDTO salvar(AtividadeDTO atividadeDTO);

    AtividadeDTO buscaPorCodigo(Long codigo);

    List<ClassificacaoTurma> listaClassificao();

    void excluir(Long codigo);
}

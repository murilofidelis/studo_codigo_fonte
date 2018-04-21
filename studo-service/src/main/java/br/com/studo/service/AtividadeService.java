package br.com.studo.service;

import br.com.studo.domain.dto.AtividadeDTO;
import br.com.studo.domain.enums.ClassificacaoTurma;

import java.util.List;

public interface AtividadeService {

    AtividadeDTO salvar(AtividadeDTO atividadeDTO);

    List<ClassificacaoTurma> listaClassificao();
}

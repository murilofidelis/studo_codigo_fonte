package br.com.studo.service;

import br.com.studo.domain.Disciplina;
import br.com.studo.domain.dto.DisciplinaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DisciplinaService {

    Page<Disciplina> filtraPesquisa(String descricao, Pageable pageable);

    DisciplinaDTO salvar(DisciplinaDTO disciplinaDTO);

    DisciplinaDTO buscarPorCodigo(Long codigo);

    Integer count();

    Iterable<DisciplinaDTO> listar();
}

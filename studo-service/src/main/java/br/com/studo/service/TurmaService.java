package br.com.studo.service;

import br.com.studo.domain.Turma;
import br.com.studo.domain.dto.TurmaDTO;
import br.com.studo.domain.enums.Periodo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TurmaService {

    Page<Turma> filtarPesquisa(List<Periodo> periodos, Integer ano, Pageable pageable);

    TurmaDTO salvar(TurmaDTO turmaDTO);

    TurmaDTO buscarPorCodigo(Long codigo);

    TurmaDTO buscaTurmaPorNumero(String numTurma);

    Integer count();
}

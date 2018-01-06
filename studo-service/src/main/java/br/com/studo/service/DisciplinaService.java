package br.com.studo.service;

import br.com.studo.domain.Disciplina;
import br.com.studo.exception.StudoException;
import br.com.studo.repository.DisciplinaRepository;
import br.com.studo.util.Mensagens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DisciplinaService {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Page<Disciplina> filtraPesquisa(String descricao, Pageable pageable) {
        return disciplinaRepository.findByDescricaoContaining(descricao, pageable);
    }

    public Disciplina salvar(Disciplina disciplina) {
        if (verificaDisciplinaExiste(disciplina.getDescricao())) {
            throw new StudoException(Mensagens.MSG007.getValue());
        }
        return disciplinaRepository.save(disciplina);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Disciplina buscarPorCodigo(Long codigo) {
        return disciplinaRepository.findOne(codigo);
    }

    private Boolean verificaDisciplinaExiste(String descricao) {
        return disciplinaRepository.buscaDisciplinaPorNome(descricao);
    }
}

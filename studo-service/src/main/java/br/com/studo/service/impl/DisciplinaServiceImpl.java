package br.com.studo.service.impl;

import br.com.studo.domain.Disciplina;
import br.com.studo.domain.dto.DisciplinaDTO;
import br.com.studo.domain.mapper.DisciplinaMapper;
import br.com.studo.exception.StudoException;
import br.com.studo.repository.DisciplinaRepository;
import br.com.studo.service.DisciplinaService;
import br.com.studo.util.Mensagem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DisciplinaServiceImpl implements DisciplinaService {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private DisciplinaMapper disciplinaMapper;

    @Autowired
    private Mensagem mensagem;

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Page<Disciplina> filtraPesquisa(String descricao, Pageable pageable) {
        return disciplinaRepository.findByDescricaoContainingIgnoreCase(descricao, pageable);
    }

    public DisciplinaDTO salvar(DisciplinaDTO disciplinaDTO) {
        if (verificaDisciplinaExiste(disciplinaDTO.getDescricao())) {
            throw new StudoException(mensagem.get("MSG001"));
        }
        return disciplinaMapper.toDTO(disciplinaRepository.save(disciplinaMapper.toEntity(disciplinaDTO)));
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public DisciplinaDTO buscarPorCodigo(Long codigo) {
        return disciplinaMapper.toDTO(disciplinaRepository.findOne(codigo));
    }

    private Boolean verificaDisciplinaExiste(String descricao) {
        return disciplinaRepository.buscaDisciplinaPorNome(descricao);
    }

    public Integer count() {
        return disciplinaRepository.quantidade();
    }

    @Override
    @Cacheable("disciplinas")
    public Iterable<DisciplinaDTO> listar() {
        return disciplinaMapper.iterable(disciplinaRepository.findAll());
    }
}

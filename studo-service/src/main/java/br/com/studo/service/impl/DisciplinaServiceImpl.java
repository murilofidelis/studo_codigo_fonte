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
import org.springframework.transaction.annotation.Transactional;

@Service
public class DisciplinaServiceImpl implements DisciplinaService {

    @Autowired
    private DisciplinaRepository repository;

    @Autowired
    private DisciplinaMapper disciplinaMapper;

    @Autowired
    private Mensagem mensagem;

    @Override

    public Page<Disciplina> filtraPesquisa(String descricao, Pageable pageable) {
        return repository.findByDescricaoContainingIgnoreCase(descricao, pageable);
    }

    @Override
    @Transactional
    public DisciplinaDTO salvar(DisciplinaDTO disciplinaDTO) {
        if (verificaDisciplinaExiste(disciplinaDTO.getDescricao())) {
            throw new StudoException(mensagem.get("MSG001"));
        }
        return disciplinaMapper.toDTO(repository.save(disciplinaMapper.toEntity(disciplinaDTO)));
    }

    @Override
    public DisciplinaDTO buscarPorCodigo(Long codigo) {
        return disciplinaMapper.toDTO(repository.findOne(codigo));
    }

    private Boolean verificaDisciplinaExiste(String descricao) {
        return repository.buscaDisciplinaPorNome(descricao);
    }

    @Override
    public Integer count() {
        return repository.quantidade();
    }

    @Override
    @Cacheable("disciplinas")
    public Iterable<DisciplinaDTO> listar() {
        return disciplinaMapper.iterable(repository.findAll());
    }
}

package br.com.studo.service.impl;

import br.com.studo.domain.dto.AtividadeDTO;
import br.com.studo.domain.mapper.AtividadeMapper;
import br.com.studo.repository.AtividadeRepository;
import br.com.studo.service.AtividadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AtividadeServiceImpl implements AtividadeService {

    @Autowired
    private AtividadeRepository atividadeRepository;

    @Autowired
    private AtividadeMapper atividadeMapper;

    @Override
    public AtividadeDTO salvar(AtividadeDTO atividadeDTO) {
        return atividadeMapper.toDTO(atividadeRepository.save(atividadeMapper.toEntity(atividadeDTO)));
    }
}

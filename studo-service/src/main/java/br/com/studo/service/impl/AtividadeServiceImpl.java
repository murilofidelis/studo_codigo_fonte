package br.com.studo.service.impl;

import br.com.studo.domain.dto.AtividadeDTO;
import br.com.studo.domain.enums.ClassificacaoTurma;
import br.com.studo.domain.mapper.AtividadeMapper;
import br.com.studo.repository.AtividadeRepository;
import br.com.studo.security.SecurityUtil;
import br.com.studo.service.AtividadeService;
import br.com.studo.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class AtividadeServiceImpl implements AtividadeService {

    @Autowired
    private AtividadeRepository atividadeRepository;

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private AtividadeMapper atividadeMapper;

    @Override
    public Page<AtividadeDTO> buscaAtividades(MultiValueMap<String, String> parametros, Pageable pageable) {

        Long cod = professorService.buscaCodProfessorProCPF(SecurityUtil.getUsuarioLogado());

        List<AtividadeDTO> listaAtividades = atividadeRepository.buscaAtividades(null, null, cod, pageable);

        //   Long qtd = atividadeRepository.quantidade(null, null, cod, pageable);

        return new PageImpl<>(listaAtividades, pageable, listaAtividades.size());

    }

    @Override
    public AtividadeDTO salvar(AtividadeDTO atividadeDTO) {
        atividadeDTO.setDataCadastro(LocalDate.now());
        atividadeDTO.setProfessor(professorService.buscarProfessorLogado());
        return atividadeMapper.toDTO(atividadeRepository.save(atividadeMapper.toEntity(atividadeDTO)));
    }

    @Override
    public List<ClassificacaoTurma> listaClassificao() {
        return Arrays.asList(ClassificacaoTurma.values());
    }
}

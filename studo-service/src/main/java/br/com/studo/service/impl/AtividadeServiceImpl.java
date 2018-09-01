package br.com.studo.service.impl;

import br.com.studo.domain.Atividade;
import br.com.studo.domain.dto.AtividadeConsultaDTO;
import br.com.studo.domain.dto.AtividadeDTO;
import br.com.studo.domain.enums.ClassificacaoTurma;
import br.com.studo.domain.mapper.AtividadeMapper;
import br.com.studo.repository.AtividadeRepository;
import br.com.studo.security.SecurityUtil;
import br.com.studo.service.AtividadeService;
import br.com.studo.service.ProfessorService;
import br.com.studo.service.filter.AtividadeFiltro;
import br.com.studo.util.DataUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;

import java.sql.Date;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class AtividadeServiceImpl implements AtividadeService {

    @Autowired
    private AtividadeRepository repository;

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private AtividadeMapper atividadeMapper;

    @Override
    public Page<AtividadeDTO> filtraPesquisa(AtividadeFiltro filtro, Pageable pageable) {
        Long codigoProfessor = professorService.buscaCodProfessorPorCPF(SecurityUtil.getUsuarioLogado());
        filtro.setCodigoProfessor(codigoProfessor);
        Page<Atividade> page = repository.findAll(filtro.filtro(), pageable);
        return page.map(atividadeMapper::toDTO);
    }

    @Override
    @Deprecated
    public Page<AtividadeConsultaDTO> buscaAtividades(MultiValueMap<String, String> parametros, Pageable pageable) {
        Date dataInicio = null;
        Date dataFim = null;

        Long cod = professorService.buscaCodProfessorPorCPF(SecurityUtil.getUsuarioLogado());

        if (Objects.nonNull(parametros.getFirst("dataInicio"))) {
            dataInicio = formataDataInfomada(parametros.getFirst("dataInicio"));
        }
        if (Objects.nonNull(parametros.getFirst("dataFim"))) {
            dataFim = formataDataInfomada(parametros.getFirst("dataFim"));
        }
        List<AtividadeConsultaDTO> listaAtividades = repository.buscaAtividades(dataInicio, dataFim, cod, pageable);

        Long quantidade = repository.quantidade(dataInicio, dataFim, cod);

        return new PageImpl<>(listaAtividades, pageable, quantidade);
    }

    private Date formataDataInfomada(String data) {
        try {
            return DataUtil.formatDate(data);
        } catch (ParseException e) {
            log.error("Erro ao converter data", e);
            return null;
        }
    }

    @Override
    @Transactional
    public AtividadeDTO salvar(AtividadeDTO atividadeDTO) {
        atividadeDTO.setDataCadastro(LocalDate.now());
        atividadeDTO.setProfessor(professorService.buscarProfessorLogado());
        return atividadeMapper.toDTO(repository.save(atividadeMapper.toEntity(atividadeDTO)));
    }

    @Override
    public AtividadeDTO buscaPorCodigo(Long codigo) {
        return atividadeMapper.toDTO(repository.findOne(codigo));
    }

    @Override
    public List<ClassificacaoTurma> listaClassificao() {
        return Arrays.asList(ClassificacaoTurma.values());
    }

    @Override
    @Transactional
    public void excluir(Long codigo) {
        repository.delete(codigo);
    }
}

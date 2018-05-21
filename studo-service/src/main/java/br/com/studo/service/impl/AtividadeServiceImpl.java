package br.com.studo.service.impl;

import br.com.studo.domain.dto.AtividadeDTO;
import br.com.studo.domain.enums.ClassificacaoTurma;
import br.com.studo.domain.mapper.AtividadeMapper;
import br.com.studo.repository.AtividadeRepository;
import br.com.studo.security.SecurityUtil;
import br.com.studo.service.AtividadeService;
import br.com.studo.service.ProfessorService;
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
        Date dataInicio = null;
        Date dataFim = null;

        Long cod = professorService.buscaCodProfessorProCPF(SecurityUtil.getUsuarioLogado());

        if (Objects.nonNull(parametros.getFirst("dataInicio"))) {
            dataInicio = formataDataInfomada(parametros.getFirst("dataInicio"));
        }
        if (Objects.nonNull(parametros.getFirst("dataFim"))) {
            dataFim = formataDataInfomada(parametros.getFirst("dataFim"));
        }
        List<AtividadeDTO> listaAtividades = atividadeRepository.buscaAtividades(dataInicio, dataFim, cod, pageable);
        Long quantidade = atividadeRepository.quantidade(dataInicio, dataFim, cod, pageable);
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
    public AtividadeDTO salvar(AtividadeDTO atividadeDTO) {
        atividadeDTO.setDataCadastro(LocalDate.now());
        atividadeDTO.setProfessor(professorService.buscarProfessorLogado());
        return atividadeMapper.toDTO(atividadeRepository.save(atividadeMapper.toEntity(atividadeDTO)));
    }

    @Override
    public AtividadeDTO buscaPorCodigo(Long codigo) {
        return atividadeMapper.toDTO(atividadeRepository.findOne(codigo));
    }

    @Override
    public List<ClassificacaoTurma> listaClassificao() {
        return Arrays.asList(ClassificacaoTurma.values());
    }

    @Override
    public void excluir(Long codigo) {
        atividadeRepository.delete(codigo);
    }
}

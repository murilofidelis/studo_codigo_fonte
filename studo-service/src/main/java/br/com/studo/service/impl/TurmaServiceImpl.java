package br.com.studo.service.impl;

import br.com.studo.domain.Turma;
import br.com.studo.domain.dto.TurmaDTO;
import br.com.studo.domain.enums.Periodo;
import br.com.studo.domain.mapper.TurmaMapper;
import br.com.studo.exception.StudoException;
import br.com.studo.repository.TurmaRepository;
import br.com.studo.service.TurmaService;
import br.com.studo.util.DataUtil;
import br.com.studo.util.Mensagem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TurmaServiceImpl implements TurmaService {

    @Autowired
    private TurmaRepository repository;

    @Autowired
    private TurmaMapper turmaMapper;

    @Autowired
    private Mensagem mensagem;

    @Override
    public Page<Turma> filtarPesquisa(List<Periodo> periodos, Integer ano, Pageable pageable) {
        return repository.findPeriodoAndAno(periodos, ano, pageable);
    }

    @Override
    @Transactional
    public TurmaDTO salvar(TurmaDTO turmaDTO) {
        turmaDTO.setNumeroTurma(gerarNumeroTurma(turmaDTO));
        if (turmaDTO.getCodigo() == null) {
            verificaTurmaCadastrada(turmaDTO.getNumeroTurma());
        }
        return turmaMapper.toDTO(repository.save(turmaMapper.toEntity(turmaDTO)));
    }

    @Override
    public TurmaDTO buscarPorCodigo(Long codigo) {
        return turmaMapper.toDTO(repository.findOne(codigo));
    }

    private String gerarNumeroTurma(TurmaDTO turmaDTO) {
        return new StringBuilder()
                .append(turmaDTO.getPeriodo().name().substring(0, 1))
                .append(turmaDTO.getAno())
                .append(turmaDTO.getSerie().substring(0, 1))
                .append(turmaDTO.getDescricaoTurma())
                .toString();
    }

    @Override
    public TurmaDTO buscaTurmaPorNumero(String numTurma) {
        return turmaMapper.toDTO(repository.findByNumeroTurma(numTurma));
    }

    private void verificaTurmaCadastrada(String numTruma) {
        if (repository.findTurmaCadastrada(numTruma)) {
            throw new StudoException(mensagem.get("MSG001"));
        }
    }

    @Override
    public Integer count() {
        return repository.quantidade(DataUtil.anoAtual());
    }
}

package br.com.studo.service;

import br.com.studo.domain.Turma;
import br.com.studo.domain.enums.Periodo;
import br.com.studo.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Page<Turma> filtarPesquisa(List<Periodo> periodos, Integer ano, Pageable pageable) {
        return turmaRepository.findPeriodoAndAno(periodos, ano, pageable);
    }

    public Turma salvar(Turma turma) {
        turma.setNumeroTurma(gerarNumeroTurma(turma));
        return turmaRepository.save(turma);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Turma buscarPorCodigo(Long codigo) {
        return turmaRepository.findOne(codigo);
    }

    private String gerarNumeroTurma(Turma turma) {
        return new StringBuilder()
                .append(turma.getPeriodo().name().substring(0, 1))
                .append(turma.getAno())
                .append(turma.getSerie().substring(0, 1))
                .append(turma.getDescricaoTurma())
                .toString();
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Turma buscaTurmaPorNumero(String numTurma) {
        return turmaRepository.findByNumeroTurma(numTurma);
    }

}

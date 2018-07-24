package br.com.studo.service.impl;

import br.com.studo.domain.Matricula;
import br.com.studo.exception.StudoException;
import br.com.studo.repository.MatriculaRepository;
import br.com.studo.service.MatriculaService;
import br.com.studo.util.DataUtil;
import br.com.studo.util.Mensagem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class MatriculaServiceImpl implements MatriculaService {

    @Autowired
    private MatriculaRepository repository;

    @Autowired
    private Mensagem mensagem;

    @Override
    @Transactional
    public Matricula salvaMatricula(Matricula matricula) {
        verificaMatricula(matricula);
        matricula.setDataMatricula(LocalDate.now());
        matricula.setTurmaAtual(true);
        matricula.setNumMatricula(geraMatricula(matricula));
        return repository.save(matricula);
    }

    private String geraMatricula(Matricula matricula) {
        return new StringBuilder()
                .append(DataUtil.anoAtual())
                .append(matricula.getTurma().getSerie().substring(0, 1))
                .append(matricula.getTurma().getDescricaoTurma())
                .append(matricula.getAluno().getCodigo())
                .toString();
    }

    @Override
    public List<Matricula> buscaMatriculasPorAluno(Long codigo) {
        return repository.findByAlunoCodigo(codigo);
    }

    private void verificaMatricula(Matricula matricula) {
        if (repository.findByAlunoMatriculado(matricula.getAluno().getCodigo(), matricula.getTurma().getCodigo())) {
            throw new StudoException(mensagem.get("MSG002"));
        }
    }

    @Override
    public void deletaMatriculaAluno(Long codMatricula) {
        repository.delete(codMatricula);
    }
}

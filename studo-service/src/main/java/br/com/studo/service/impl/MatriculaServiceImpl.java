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

@Transactional
@Service
public class MatriculaServiceImpl implements MatriculaService {

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private Mensagem mensagem;

    public Matricula salvaMatricula(Matricula matricula) {
        verificaMatricula(matricula);
        matricula.setDataMatricula(LocalDate.now());
        matricula.setTurmaAtual(true);
        matricula.setMatricula(geraMatricula(matricula));
        return matriculaRepository.save(matricula);
    }

    private String geraMatricula(Matricula matricula) {
        return new StringBuilder()
                .append(DataUtil.anoAtual())
                .append(matricula.getTurma().getSerie().substring(0, 1))
                .append(matricula.getTurma().getDescricaoTurma())
                .append(matricula.getAluno().getCodigo())
                .toString();
    }

    public List<Matricula> buscaMatriculasPorAluno(Long codigo) {
        return matriculaRepository.findByAlunoCodigo(codigo);
    }

    private void verificaMatricula(Matricula matricula) {
        if (matriculaRepository.findByAlunoMatriculado(matricula.getAluno().getCodigo(), matricula.getTurma().getCodigo())) {
            throw new StudoException(mensagem.get("MSG002"));
        }
    }

    public void deletaMatriculaAluno(Long codMatricula) {
        matriculaRepository.delete(codMatricula);
    }
}

package br.com.studo.service;

import br.com.studo.domain.Matricula;
import br.com.studo.exception.StudoException;
import br.com.studo.repository.MatriculaRepository;
import br.com.studo.util.Mensagens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

@Transactional
@Service
public class MatriculaService {

    @Autowired
    private MatriculaRepository matriculaRepository;

    public Matricula salvaMatricula(Matricula matricula) {
        verificaMatricula(matricula);
        matricula.setDataCadastro(LocalDate.now());
        matricula.setTurmaAtual(true);
        matricula.setMatricula(geraMatricula(matricula));
        return matriculaRepository.save(matricula);
    }

    private String geraMatricula(Matricula matricula) {
        Calendar calendar = Calendar.getInstance();
        int ano = calendar.get(Calendar.YEAR);
        return new StringBuilder()
                .append(ano)
                .append("/")
                .append(matricula.getTurma().getSerie().substring(0, 1))
                .append("/")
                .append(matricula.getTurma().getDescricaoTurma()).append("/")
                .append(matricula.getAluno().getCodigo())
                .toString();
    }

    protected List<Matricula> buscaMatriculasPorAluno(Long codigo) {
        return matriculaRepository.findByAlunoCodigo(codigo);
    }

    private void verificaMatricula(Matricula matricula) {
        if (matriculaRepository.findByAlunoMatriculado(matricula.getAluno().getCodigo(), matricula.getTurma().getCodigo())) {
            throw new StudoException(Mensagens.MSG041.getValue());
        }
    }
}

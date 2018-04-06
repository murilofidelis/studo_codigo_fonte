package br.com.studo.service;

import br.com.studo.domain.Matricula;

import java.util.List;

public interface MatriculaService {

    Matricula salvaMatricula(Matricula matricula);

    List<Matricula> buscaMatriculasPorAluno(Long codigo);

    void deletaMatriculaAluno(Long codMatricula);
}

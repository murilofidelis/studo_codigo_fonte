package br.com.studo.service;

import br.com.studo.domain.Aluno;
import br.com.studo.domain.dto.AlunoDTO;
import br.com.studo.domain.dto.MatriculaDTO;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface AlunoService {

    Page<Aluno> filtarPesquisar(String nome, Pageable pageable);

    Page<Aluno> filtrar(Predicate predicate, Pageable pageable);

    AlunoDTO salvarAluno(AlunoDTO alunoDTO);

    AlunoDTO buscaPorCodigo(Long codigo);

    MatriculaDTO salvaMatricula(MatriculaDTO matriculaDTO);

    List<MatriculaDTO> buscaMatriculasPorAluno(Long codigo);

    void deletaMatriculaAluno(Long codMatricula);

    boolean verificaCpfCadastrado(String cpf);

    Integer count();

    void geraRelatorioAluno(HttpServletResponse response);
}

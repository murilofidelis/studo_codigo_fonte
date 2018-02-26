package br.com.studo.service;

import br.com.studo.domain.Aluno;
import br.com.studo.domain.Matricula;
import br.com.studo.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    MatriculaService matriculaService;

    @Autowired
    private UsuarioService usuarioService;

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Page<Aluno> filtarPesquisar(String nome, Pageable pageable) {
        return alunoRepository.findByNomeStartingWithIgnoreCase(nome, pageable);
    }

    public Aluno salvarAluno(Aluno aluno) {
        if (aluno.getCodigo() == null) {
            usuarioService.criaUsuarioAluno(aluno);
        } else {
            usuarioService.atualizaUsuarioAluno(aluno);
        }
        return alunoRepository.save(aluno);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Aluno buscaPorCodigo(Long codigo) {
        return alunoRepository.findOne(codigo);
    }

    public Matricula salvaMatricula(Matricula matricula) {
        return matriculaService.salvaMatricula(matricula);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<Matricula> buscaMatriculasPorAluno(Long codigo) {
        return matriculaService.buscaMatriculasPorAluno(codigo);
    }

    public void deletaMatriculaAluno(Long codMatricula) {
        matriculaService.deletaMatriculaAluno(codMatricula);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public boolean verificaCpfCadastrado(String cpf) {
        return alunoRepository.findByCpfCadastrado(cpf);
    }

}

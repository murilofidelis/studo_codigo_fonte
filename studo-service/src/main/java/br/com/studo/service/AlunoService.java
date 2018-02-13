package br.com.studo.service;

import br.com.studo.domain.Aluno;
import br.com.studo.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Page<Aluno> filtarPesquisar(String nome, Pageable pageable) {
        return alunoRepository.findByNomeStartingWithIgnoreCase(nome, pageable);
    }

    public Aluno salvarAluno(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Aluno buscaPorCodigo(Long codigo) {
        return alunoRepository.findOne(codigo);
    }
}

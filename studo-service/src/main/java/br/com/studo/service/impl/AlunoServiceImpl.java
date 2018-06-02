package br.com.studo.service.impl;

import br.com.studo.domain.Aluno;
import br.com.studo.domain.dto.AlunoDTO;
import br.com.studo.domain.dto.MatriculaDTO;
import br.com.studo.domain.mapper.AlunoMapper;
import br.com.studo.domain.mapper.MatriculaMapper;
import br.com.studo.repository.AlunoRepository;
import br.com.studo.service.AlunoService;
import br.com.studo.service.MatriculaService;
import br.com.studo.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class AlunoServiceImpl implements AlunoService {

    @Autowired
    private AlunoRepository repository;

    @Autowired
    private AlunoMapper alunoMapper;

    @Autowired
    private MatriculaMapper matriculaMapper;

    @Autowired
    private MatriculaService matriculaService;

    @Autowired
    private UsuarioService usuarioService;

    public Page<Aluno> filtarPesquisar(String nome, Pageable pageable) {
        return repository.findByNomeStartingWithIgnoreCase(nome, pageable);
    }

    @Transactional
    public AlunoDTO salvarAluno(AlunoDTO alunoDTO) {
        Aluno aluno = alunoMapper.toEntity(alunoDTO);
        if (aluno.getCodigo() == null) {
            usuarioService.criaUsuarioAluno(aluno);
        } else {
            usuarioService.atualizaUsuarioAluno(aluno);
        }
        return alunoMapper.toDTO(repository.save(aluno));
    }

    public AlunoDTO buscaPorCodigo(Long codigo) {
        return alunoMapper.toDTO(repository.findOne(codigo));
    }

    @Transactional
    public MatriculaDTO salvaMatricula(MatriculaDTO matriculaDTO) {
        return matriculaMapper.toDTO(matriculaService.salvaMatricula(matriculaMapper.toEntity(matriculaDTO)));
    }

    public List<MatriculaDTO> buscaMatriculasPorAluno(Long codigo) {
        return matriculaMapper.listDTO(matriculaService.buscaMatriculasPorAluno(codigo));
    }

    @Transactional
    public void deletaMatriculaAluno(Long codMatricula) {
        matriculaService.deletaMatriculaAluno(codMatricula);
    }

    public boolean verificaCpfCadastrado(String cpf) {
        return usuarioService.cpfExiste(cpf);
    }

    public Integer count() {
        return repository.quantidade();
    }

}

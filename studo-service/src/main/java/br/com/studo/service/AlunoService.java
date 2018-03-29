package br.com.studo.service;

import br.com.studo.domain.Aluno;
import br.com.studo.domain.Matricula;
import br.com.studo.domain.dto.AlunoDTO;
import br.com.studo.domain.dto.MatriculaDTO;
import br.com.studo.domain.mapper.AlunoMapper;
import br.com.studo.domain.mapper.MatriculaMapper;
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
    private AlunoMapper alunoMapper;

    @Autowired
    private MatriculaMapper matriculaMapper;

    @Autowired
    MatriculaService matriculaService;

    @Autowired
    private UsuarioService usuarioService;


    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Page<Aluno> filtarPesquisar(String nome, Pageable pageable) {
        return alunoRepository.findByNomeStartingWithIgnoreCase(nome, pageable);
    }

    public AlunoDTO salvarAluno(AlunoDTO alunoDTO) {
        Aluno aluno = alunoMapper.toEntity(alunoDTO);
        if (aluno.getCodigo() == null) {
            usuarioService.criaUsuarioAluno(aluno);
        } else {
            usuarioService.atualizaUsuarioAluno(aluno);
        }
        return alunoMapper.toDTO(alunoRepository.save(aluno));
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public AlunoDTO buscaPorCodigo(Long codigo) {
        return alunoMapper.toDTO(alunoRepository.findOne(codigo));
    }

    public MatriculaDTO salvaMatricula(MatriculaDTO matriculaDTO) {
        return matriculaMapper.toDTO(matriculaService.salvaMatricula(matriculaMapper.toEntity(matriculaDTO)));
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<MatriculaDTO> buscaMatriculasPorAluno(Long codigo) {
        return matriculaMapper.listDTO(matriculaService.buscaMatriculasPorAluno(codigo));
    }

    public void deletaMatriculaAluno(Long codMatricula) {
        matriculaService.deletaMatriculaAluno(codMatricula);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public boolean verificaCpfCadastrado(String cpf) {
        return alunoRepository.findByCpfCadastrado(cpf);
    }

}

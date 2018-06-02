package br.com.studo.service.impl;

import br.com.studo.domain.Professor;
import br.com.studo.domain.dto.ProfessorDTO;
import br.com.studo.domain.mapper.ProfessorMapper;
import br.com.studo.repository.ProfessorRepository;
import br.com.studo.security.SecurityUtil;
import br.com.studo.service.ProfessorService;
import br.com.studo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProfessorServiceImpl implements ProfessorService {

    @Autowired
    private ProfessorRepository repository;

    @Autowired
    private ProfessorMapper professorMapper;

    @Autowired
    private UsuarioService usuarioService;

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Page<Professor> filtarPesquisar(String nome, Pageable pageable) {
        return repository.findByNomeStartingWithIgnoreCase(nome, pageable);
    }

    @Override
    public ProfessorDTO salvar(ProfessorDTO professorDTO) {
        Professor professor = professorMapper.toEntity(professorDTO);
        if (professor.getCodigo() == null) {
            usuarioService.criaUsuarioProfessor(professor);
        } else {
            usuarioService.atualizaUsuarioProfessor(professor);
        }
        return professorMapper.toDTO(repository.save(professor));
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public boolean verificaCpfCadastrado(String cpf) {
        return usuarioService.cpfExiste(cpf);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public ProfessorDTO buscaPorCodigo(Long codigo) {
        return professorMapper.toDTO(repository.findOne(codigo));
    }

    @Override
    public Integer count() {
        return repository.quantidade();
    }

    @Override
    public ProfessorDTO buscarProfessorLogado() {
        return professorMapper.toDTO(repository.findByCpf(SecurityUtil.getUsuarioLogado()));
    }

    @Override
    public Long buscaCodProfessorProCPF(String cpf) {
        return repository.buscaCodProfessorPorCPF(cpf);
    }

}

package br.com.studo.service.impl;

import br.com.studo.domain.Professor;
import br.com.studo.domain.dto.ProfessorDTO;
import br.com.studo.domain.mapper.ProfessorMapper;
import br.com.studo.repository.ProfessorRepositoty;
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
    private ProfessorRepositoty professorRepositoty;

    @Autowired
    private ProfessorMapper professorMapper;

    @Autowired
    private UsuarioService usuarioService;

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Page<Professor> filtarPesquisar(String nome, Pageable pageable) {
        return professorRepositoty.findByNomeStartingWithIgnoreCase(nome, pageable);
    }

    public ProfessorDTO salvar(ProfessorDTO professorDTO) {
        Professor professor = professorMapper.toEntity(professorDTO);
        if (professor.getCodigo() == null) {
            usuarioService.criaUsuarioProfessor(professor);
        } else {
            usuarioService.atualizaUsuarioProfessor(professor);
        }
        return professorMapper.toDTO(professorRepositoty.save(professor));
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public boolean verificaCpfCadastrado(String cpf) {
        return professorRepositoty.findByCpfCadastrado(cpf);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public ProfessorDTO buscaPorCodigo(Long codigo) {
        return professorMapper.toDTO(professorRepositoty.findOne(codigo));
    }

    public Integer count() {
        return professorRepositoty.quantidade();
    }

}

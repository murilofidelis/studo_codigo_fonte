package br.com.studo.service;

import br.com.studo.domain.Professor;
import br.com.studo.repository.ProfessorRepositoty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProfessorService {

    @Autowired
    private ProfessorRepositoty professorRepositoty;

    @Autowired
    private UsuarioService usuarioService;

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Page<Professor> filtarPesquisar(String nome, Pageable pageable) {
        return professorRepositoty.findByNomeStartingWithIgnoreCase(nome, pageable);
    }

    public Professor salvar(Professor professor) {
        if (professor.getCodigo() == null) {
            usuarioService.criaUsuarioProfessor(professor);
        } else {
            usuarioService.atualizaUsuarioProfessor(professor);
        }
        return professorRepositoty.save(professor);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public boolean verificaCpfCadastrado(String cpf) {
        return professorRepositoty.findByCpfCadastrado(cpf);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Professor buscaPorCodigo(Long codigo) {
        return professorRepositoty.findOne(codigo);
    }

}

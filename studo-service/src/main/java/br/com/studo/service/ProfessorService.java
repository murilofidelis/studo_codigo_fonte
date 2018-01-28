package br.com.studo.service;

import br.com.studo.domain.Professor;
import br.com.studo.domain.Usuario;
import br.com.studo.domain.enuns.Tipo;
import br.com.studo.repository.ProfessorRepositoty;
import br.com.studo.util.GeraSenhaProvisoriaUtil;
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

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Page<Professor> filtarPesquisar(String nome, Pageable pageable) {
        return professorRepositoty.findByNomeStartingWithIgnoreCase(nome, pageable);
    }

    public Professor salvar(Professor professor) {

        if (professor.getUsuario() == null) {
            professor.setUsuario(criaUsuario(professor));
        }
        return professorRepositoty.save(professor);
    }

    private Usuario criaUsuario(Professor professor) {
        Usuario usuario = new Usuario();
        usuario.setLogin(professor.getCpf());
        usuario.setSenha(GeraSenhaProvisoriaUtil.geraSenha());
        usuario.setStatus(true);
        usuario.setTipo(Tipo.PROFESSOR);
        return usuario;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public boolean verificaCpfCadastrado(String cpf) {
        return professorRepositoty.findByCpfCadastrado(cpf);
    }

}

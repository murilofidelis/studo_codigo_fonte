package br.com.studo.service;

import br.com.studo.domain.Professor;
import br.com.studo.domain.Usuario;
import br.com.studo.domain.enuns.Tipo;
import br.com.studo.repository.UsuarioRepositoty;
import br.com.studo.util.GeraSenhaProvisoriaUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepositoty usuarioRepositoty;

    public Usuario criaUsuarioProfessor(Professor professor) {
        Usuario usuario = new Usuario();
        usuario.setLogin(professor.getCpf());
        usuario.setSenha(GeraSenhaProvisoriaUtil.geraSenha());
        usuario.setStatus(professor.getStatus());
        usuario.setTipo(Tipo.PROFESSOR);
        salvaUsuario(usuario);
        log.info("SALVANDO USUÁRIO: {}", professor.getNome());
        return usuario;
    }

    public Usuario atualizaUsuarioProfessor(Professor professor) {
        Usuario usuario = buscaPorCpf(professor.getCpf());
        usuario.setStatus(professor.getStatus());
        log.info("ATUALIZANDO USUÁRIO: {}", professor.getNome());
        return usuario;
    }

    private void salvaUsuario(Usuario usuario) {
        usuarioRepositoty.save(usuario);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    private Usuario buscaPorCpf(String cpf) {
        log.info("BUSCANDO USUÁRIO CPF:  {}", cpf);
        return usuarioRepositoty.findByLogin(cpf);
    }
}

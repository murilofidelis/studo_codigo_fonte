package br.com.studo.service;

import br.com.studo.domain.Aluno;
import br.com.studo.domain.Professor;
import br.com.studo.domain.Usuario;
import br.com.studo.domain.enums.Tipo;
import br.com.studo.repository.UsuarioRepositoty;
import br.com.studo.util.PasswordUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepositoty usuarioRepositoty;

    @Autowired
    private EmailService emailService;

    public Usuario criaUsuarioProfessor(Professor professor) {
        Usuario usuario = new Usuario();
        usuario.setLogin(professor.getCpf());
        String senhaProvisoria = PasswordUtil.geraSenha();
        usuario.setSenha(PasswordUtil.encoderPassword(senhaProvisoria));
        usuario.setNome(professor.getNome());
        usuario.setEmail(professor.getEmail().getDscEmail());
        usuario.setStatus(professor.getStatus());
        usuario.setTipo(Tipo.PROFESSOR);
        salvaUsuario(usuario);

        log.info("SALVANDO USUÁRIO: {}", professor.getNome());

/*		emailService.enviaEmail(professor.getEmail().getDscEmail(), "Studo - Criação de usuário",
				criarMensagemEmail(professor.getCpf(), professor.getNome(), senhaProvisoria));*/

        return usuario;
    }

    public Usuario atualizaUsuarioProfessor(Professor professor) {
        Usuario usuario = buscaPorCpf(professor.getCpf());
        usuario.setStatus(professor.getStatus());
        log.info("ATUALIZANDO USUÁRIO: {}", professor.getNome());
        return usuario;
    }

    public Usuario criaUsuarioAluno(Aluno aluno) {
        Usuario usuario = new Usuario();
        usuario.setNome(aluno.getNome());
        usuario.setLogin(aluno.getCpf());
        String senhaProvisoria = PasswordUtil.geraSenha();
        usuario.setSenha(PasswordUtil.encoderPassword(senhaProvisoria));
        usuario.setEmail(aluno.getEmail().getDscEmail());
        usuario.setStatus(aluno.getStatus());
        usuario.setTipo(Tipo.ALUNO);
        salvaUsuario(usuario);

        log.info("SALVANDO USUÁRIO: {}", aluno.getNome());

/*		emailService.enviaEmail(aluno.getEmail().getDscEmail(), "Studo - Criação de usuário",
				criarMensagemEmail(aluno.getCpf(), aluno.getNome(), senhaProvisoria));*/

        return usuario;
    }

    public Usuario atualizaUsuarioAluno(Aluno aluno) {
        Usuario usuario = buscaPorCpf(aluno.getCpf());
        usuario.setStatus(aluno.getStatus());
        log.info("ATUALIZANDO USUÁRIO: {}", aluno.getNome());
        return usuario;
    }

    private void salvaUsuario(Usuario usuario) {
        usuarioRepositoty.save(usuario);
    }

    private Usuario buscaPorCpf(String cpf) {
        log.info("BUSCANDO USUÁRIO CPF:  {}", cpf);
        return usuarioRepositoty.findByLogin(cpf);
    }

    private String criarMensagemEmail(String cpf, String nome, String senhaProvisoria) {

        return new StringBuilder().append("PREZADO(a), " + nome)
                .append("\n")
                .append("Sua consta de usuário foi criada com sucesso!")
                .append("\n\n")
                .append("Segue sua senha provisoria: ")
                .append("\n\n")
                .append("Usuario: " + cpf)
                .append("\n")
                .append("Senha provisória: " + senhaProvisoria)
                .toString();
    }
}

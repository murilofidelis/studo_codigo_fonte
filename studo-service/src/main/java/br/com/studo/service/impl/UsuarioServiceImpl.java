package br.com.studo.service.impl;

import br.com.studo.config.StudoProperty;
import br.com.studo.domain.Aluno;
import br.com.studo.domain.Professor;
import br.com.studo.domain.enums.TipoPerfil;
import br.com.studo.domain.usuario.Perfil;
import br.com.studo.domain.usuario.Usuario;
import br.com.studo.repository.UsuarioRepositoty;
import br.com.studo.service.EmailService;
import br.com.studo.service.PerfilService;
import br.com.studo.service.UsuarioService;
import br.com.studo.util.PasswordUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepositoty repositoty;

    @Autowired
    private PerfilService perfilService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private StudoProperty property;

    @Override
    @Transactional
    public Usuario criaUsuarioProfessor(Professor professor) {
        Usuario usuario = new Usuario();
        usuario.setLogin(professor.getCpf());
        String senhaProvisoria = PasswordUtil.geraSenha();
        usuario.setSenha(PasswordUtil.encoderPassword(senhaProvisoria));
        usuario.setNome(professor.getNome());
        usuario.setEmail(professor.getEmail().getDscEmail());
        usuario.setStatus(professor.getStatus());
        usuario.setPerfils(addPerfil(TipoPerfil.PROFESSOR));
        salvaUsuario(usuario);

        log.debug("SALVANDO USUÁRIO: {}", professor.getNome() + " CPF: " + professor.getCpf() + " SENHA PROVISORIA: " + senhaProvisoria);

        if (property.isEnviarEmail()) {
            emailService.enviaEmail(professor.getEmail().getDscEmail(), "Studo - Criação de usuário",
                    criarMensagemEmail(professor.getCpf(), professor.getNome(), senhaProvisoria));
        }
        return usuario;
    }

    @Override
    @Transactional
    public Usuario atualizaUsuarioProfessor(Professor professor) {
        Usuario usuario = buscaPorCpf(professor.getCpf());
        usuario.setNome(professor.getNome());
        usuario.setEmail(professor.getEmail().getDscEmail());
        usuario.setStatus(professor.getStatus());
        log.debug("ATUALIZANDO USUÁRIO: {}", professor.getNome());
        return usuario;
    }

    @Override
    @Transactional
    public Usuario criaUsuarioAluno(Aluno aluno) {
        Usuario usuario = new Usuario();
        usuario.setNome(aluno.getNome());
        usuario.setLogin(aluno.getCpf());
        String senhaProvisoria = PasswordUtil.geraSenha();
        usuario.setSenha(PasswordUtil.encoderPassword(senhaProvisoria));
        usuario.setEmail(aluno.getEmail().getDscEmail());
        usuario.setStatus(aluno.getStatus());
        usuario.setPerfils(addPerfil(TipoPerfil.ALUNO));
        salvaUsuario(usuario);

        log.debug("SALVANDO USUÁRIO: {}", aluno.getNome() + " CPF: " + aluno.getCpf() + " SENHA PROVISORIA: " + senhaProvisoria);

        if (property.isEnviarEmail()) {
            emailService.enviaEmail(aluno.getEmail().getDscEmail(), "Studo - Criação de usuário",
                    criarMensagemEmail(aluno.getCpf(), aluno.getNome(), senhaProvisoria));
        }
        return usuario;
    }

    @Override
    @Transactional
    public Usuario atualizaUsuarioAluno(Aluno aluno) {
        Usuario usuario = buscaPorCpf(aluno.getCpf());
        usuario.setNome(aluno.getNome());
        usuario.setEmail(aluno.getEmail().getDscEmail());
        usuario.setStatus(aluno.getStatus());
        log.debug("ATUALIZANDO USUÁRIO: {}", aluno.getNome());
        return usuario;
    }

    @Override
    public Boolean cpfExiste(String cpf) {
        return repositoty.veificaCpf(cpf);
    }

    private void salvaUsuario(Usuario usuario) {
        repositoty.save(usuario);
    }

    private Set<Perfil> addPerfil(TipoPerfil tipoPerfil) {
        Set<Perfil> perfils = new HashSet<>();
        Perfil perfil = perfilService.getPerfil(tipoPerfil.getCodigo());
        perfils.add(perfil);
        return perfils;
    }

    private Usuario buscaPorCpf(String cpf) {
        log.debug("BUSCANDO USUÁRIO CPF:  {}", cpf);
        return repositoty.findByLogin(cpf);
    }

    private String criarMensagemEmail(String cpf, String nome, String senhaProvisoria) {
        return new StringBuilder().append("PREZADO(a), " + nome)
                .append("\n")
                .append("Sua conta de usuário foi criada com sucesso!")
                .append("\n\n")
                .append("Segue sua senha provisoria: ")
                .append("\n\n")
                .append("Usuario: " + cpf)
                .append("\n")
                .append("Senha provisória: " + senhaProvisoria)
                .toString();
    }
}

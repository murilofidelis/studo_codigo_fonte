package br.com.studo.service;

import br.com.studo.domain.Aluno;
import br.com.studo.domain.Professor;
import br.com.studo.domain.usuario.Usuario;

public interface UsuarioService {

    Usuario criaUsuarioProfessor(Professor professor);

    Usuario atualizaUsuarioProfessor(Professor professor);

    Usuario criaUsuarioAluno(Aluno aluno);

    Usuario atualizaUsuarioAluno(Aluno aluno);
}

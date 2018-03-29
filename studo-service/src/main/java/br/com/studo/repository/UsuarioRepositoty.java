package br.com.studo.repository;

import br.com.studo.domain.usuario.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepositoty extends CrudRepository<Usuario, Long> {

    Usuario findByLoginAndStatus(String cpf, boolean status);

    Usuario findByLogin(String cpf);
}

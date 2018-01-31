package br.com.studo.repository;

import br.com.studo.domain.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepositoty extends CrudRepository<Usuario, Long> {

    Usuario findByLogin(String cpf);
}

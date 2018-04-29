package br.com.studo.repository;

import br.com.studo.domain.usuario.Perfil;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilRepository extends CrudRepository<Perfil, Long> {
}

package br.com.studo.security;

import br.com.studo.domain.usuario.Usuario;
import br.com.studo.repository.UsuarioRepositoty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Service
public class AppUserDetailService implements UserDetailsService {

    @Autowired
    private UsuarioRepositoty usuarioRepositoty;

    @Override
    public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepositoty.findByLoginAndStatus(cpf, true);

        if (Objects.isNull(usuario)) {
            throw new UsernameNotFoundException("Usuário ou senha inválidos");
        }
        return new UsuarioSistema(usuario, getPermissoes(usuario));
    }

    private Collection<? extends GrantedAuthority> getPermissoes(Usuario usuario) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        usuario.getPerfils().forEach(perfis -> perfis.getPermissoes().forEach(permissao -> authorities.add(new SimpleGrantedAuthority(permissao.getDescricao().toUpperCase()))));
        return authorities;
    }
}

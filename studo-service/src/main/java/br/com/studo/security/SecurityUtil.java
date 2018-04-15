package br.com.studo.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

public final class SecurityUtil {

    private SecurityUtil() {
    }

    /* Métdo responsável por retornar o login do usuário logado */

    public static String getUsuarioLogado() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String login = null;
        if (Objects.nonNull(authentication)) {
            login = (String) authentication.getPrincipal();
        }
        return login;
    }
}

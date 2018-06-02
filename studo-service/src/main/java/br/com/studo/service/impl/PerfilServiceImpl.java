package br.com.studo.service.impl;

import br.com.studo.domain.usuario.Perfil;
import br.com.studo.repository.PerfilRepository;
import br.com.studo.service.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PerfilServiceImpl implements PerfilService {

    @Autowired
    private PerfilRepository repository;

    @Override
    public Perfil getPerfil(Long codigo) {
        return repository.findOne(codigo);
    }

}

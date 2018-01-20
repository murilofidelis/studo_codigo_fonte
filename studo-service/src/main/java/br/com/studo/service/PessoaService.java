package br.com.studo.service;

import br.com.studo.domain.Professor;
import br.com.studo.repository.PessoaRepositoty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PessoaService {

    @Autowired
    private PessoaRepositoty pessoaRepositoty;

    public Professor salvar(Professor pessoa) {
        return pessoaRepositoty.save(pessoa);
    }
}

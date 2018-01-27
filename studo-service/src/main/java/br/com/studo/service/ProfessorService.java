package br.com.studo.service;

import br.com.studo.domain.Professor;
import br.com.studo.repository.ProfessorRepositoty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProfessorService {

    @Autowired
    private ProfessorRepositoty pessoaRepositoty;

    public Professor salvar(Professor pessoa) {
        return pessoaRepositoty.save(pessoa);
    }
}

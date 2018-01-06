package br.com.studo.service;

import br.com.studo.domain.Turma;
import br.com.studo.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;

    public Turma salvar(Turma turma) {
        return turmaRepository.save(turma);
    }
}

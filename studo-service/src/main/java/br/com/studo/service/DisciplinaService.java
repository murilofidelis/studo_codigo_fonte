package br.com.studo.service;

import br.com.studo.domain.Disciplina;
import br.com.studo.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DisciplinaService {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    public Page<Disciplina> buscaTodos(String descricao, Pageable pageable) {
        return disciplinaRepository.findByDescricaoContaining(descricao, pageable);
    }

    public Disciplina salvar(Disciplina disciplina) {
        return disciplinaRepository.save(disciplina);
    }

    public Disciplina buscarPorCodigo(Long codigo) {
        return disciplinaRepository.findOne(codigo);
    }

    public  Disciplina atualizar(Disciplina disciplina){
        return  disciplinaRepository.save(disciplina);
    }


}

package br.com.studo.test;

import br.com.studo.domain.Disciplina;
import br.com.studo.domain.mapper.DisciplinaMapper;
import br.com.studo.repository.DisciplinaRepository;
import br.com.studo.service.DisciplinaService;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DisciplinaTest {

    @Autowired
    DisciplinaService service;

    @Autowired
    private DisciplinaMapper disciplinaMapper;

    @Autowired
    private DisciplinaRepository repository;

    @Test
    @Ignore
    public void salvar() {
        Disciplina d = new Disciplina();
        d.setDescricao("TESTE 222");
        d.setAtiva(true);
        service.salvar(disciplinaMapper.toDTO(d));
    }

    @Test
    public void buscar() {
        List<Disciplina> disciplinas = (List<Disciplina>) repository.findAll();
        Assert.assertTrue(disciplinas.size() > 0);

    }

}

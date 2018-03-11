package br.com.studo.test;

import br.com.studo.domain.Disciplina;
import br.com.studo.repository.DisciplinaRepository;
import br.com.studo.service.DisciplinaService;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DisciplinaTest {

	@Autowired
	DisciplinaService service;

	@Autowired
	DisciplinaRepository repository;

	@Test
	@Ignore
	public void salvar() {

		Disciplina d = new Disciplina();
		d.setDescricao("TESTE 22");
		d.setAtiva(true);
		service.salvar(d);

	}

	@Test
	public void buscar() {
		List<Disciplina> disciplinas = (List<Disciplina>) repository.findAll();
		
		disciplinas.forEach(d -> System.out.println(d.getDescricao()));

	}

}

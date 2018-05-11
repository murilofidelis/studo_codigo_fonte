package br.com.studo.test.service;


import br.com.studo.domain.dto.TurmaDTO;
import br.com.studo.service.impl.TurmaServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Objects;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TurmaServiceTest {


    @Autowired
    private TurmaServiceImpl turmaService;


    @Test
    public void buscaPorId() {

        TurmaDTO result = turmaService.buscarPorCodigo(1L);
        assertEquals(true, Objects.nonNull(result));

    }


}

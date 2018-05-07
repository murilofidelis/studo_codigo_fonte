package br.com.studo.test.service;

import br.com.studo.domain.dto.DisciplinaDTO;
import br.com.studo.service.DisciplinaService;
import br.com.studo.service.impl.DisciplinaServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DisciplinaServiceTest {

    @Mock
    DisciplinaServiceImpl disciplinaService;

    @Test
    public void cout() {
        when(disciplinaService.count()).thenReturn(3);
        assertTrue(disciplinaService.count() == 3);
    }

    @Test
    public void listaTodas() {
        DisciplinaDTO d1 = new DisciplinaDTO(1L, "ARTES", true);
        DisciplinaDTO d2 = new DisciplinaDTO(2L, "CIÊNCIAS", true);
        DisciplinaDTO d3 = new DisciplinaDTO(3L, "MATEMÁTICA", true);
        List<DisciplinaDTO> disciplinaDTOList = Arrays.asList(d1, d2, d3);
        when(disciplinaService.listar()).thenReturn(disciplinaDTOList);
        List<DisciplinaDTO> lista = (List<DisciplinaDTO>) disciplinaService.listar();
        assertEquals(3, lista.size());
        assertEquals(false, lista.isEmpty());
        assertEquals("ARTES", lista.get(0).getDescricao());
    }

    @Test
    public void buscaPorId() {
        DisciplinaDTO dto = new DisciplinaDTO(1L, "ARTES", false);
        when(disciplinaService.buscarPorCodigo(1L)).thenReturn(dto);
        DisciplinaDTO result = disciplinaService.buscarPorCodigo(1L);
        assertEquals(dto.getCodigo(), result.getCodigo());
        assertEquals("ARTES", result.getDescricao());
        assertEquals(false, result.getAtiva());
    }

    @Test
    public void salvar() {
        DisciplinaDTO dto = new DisciplinaDTO(1L, "ARTES", true);
        when(disciplinaService.salvar(dto)).thenReturn(dto);
        DisciplinaDTO result = disciplinaService.salvar(dto);
        assertEquals(true, result.getAtiva());
        assertEquals(true, result.getDescricao().equals("ARTES"));
    }

}

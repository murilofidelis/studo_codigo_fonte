package br.com.studo.test.service;

import br.com.studo.domain.Disciplina;
import br.com.studo.domain.dto.DisciplinaDTO;
import br.com.studo.domain.mapper.DisciplinaMapper;
import br.com.studo.exception.StudoException;
import br.com.studo.repository.DisciplinaRepository;
import br.com.studo.service.impl.DisciplinaServiceImpl;
import br.com.studo.util.Mensagem;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DisciplinaServiceImpl.class)
public class DisciplinaServiceTest {

    @InjectMocks
    DisciplinaServiceImpl disciplinaService;

    @Mock
    DisciplinaDTO dto;

    @Mock
    DisciplinaRepository repository;

    @Mock
    DisciplinaMapper mapper;

    @Mock
    Disciplina disciplina;

    @Mock
    Pageable pageable;

    @Mock
    Mensagem mensagem;

    private DisciplinaServiceImpl service;

    @Before
    public void setUp() {
        when(mapper.toEntity(any(DisciplinaDTO.class))).thenReturn(disciplina);
        when(mapper.toDTO(any(Disciplina.class))).thenReturn(dto);
    }

    @Test
    public void salva() {
        dto = new DisciplinaDTO(1L, "ARTES", true);
        DisciplinaDTO disciplinaDTO = disciplinaService.salvar(dto);
        assertNotNull(disciplinaDTO);
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
    public void ccount() {
        when(repository.quantidade()).thenReturn(10);
        Integer qtd = disciplinaService.count();
        assertEquals(true, qtd == 10);
    }

    @Test
    public void filtarPesquisa() {
        Disciplina disciplina = new Disciplina();
        disciplina.setCodigo(1L);
        disciplina.setDescricao("ARTES");
        disciplina.setAtiva(true);
        List<Disciplina> lista = Arrays.asList(disciplina);
        Page<Disciplina> pageDisciplina = new PageImpl<>(lista);
        when(disciplinaService.filtraPesquisa("TESTE", this.pageable)).thenReturn(pageDisciplina);
        Page<Disciplina> page = disciplinaService.filtraPesquisa("TESTE", this.pageable);
        assertNotNull(page);
    }

    @Test
    public void buscaPorCodigo() {
        when(repository.findOne(1L)).thenReturn(disciplina);
        DisciplinaDTO dto = disciplinaService.buscarPorCodigo(1L);
        assertNotNull(dto);
    }

    @Test(expected = StudoException.class)
    public void disciplinCadastrada() throws Exception {
        when(repository.buscaDisciplinaPorNome(any(String.class))).thenReturn(true);
        DisciplinaServiceImpl disciplinaServiceSpy = PowerMockito.spy(new DisciplinaServiceImpl());
        doReturn(true).when(disciplinaServiceSpy, "verificaDisciplinaExiste", any(String.class));
        when(mensagem.get(any(String.class))).thenReturn("Já cadastrada");
        disciplinaService.salvar(new DisciplinaDTO());
    }
}

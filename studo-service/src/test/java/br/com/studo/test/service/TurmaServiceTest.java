package br.com.studo.test.service;


import br.com.studo.domain.Turma;
import br.com.studo.domain.dto.TurmaDTO;
import br.com.studo.domain.mapper.TurmaMapper;
import br.com.studo.repository.TurmaRepository;
import br.com.studo.service.impl.TurmaServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(TurmaServiceImpl.class)
public class TurmaServiceTest {

    @InjectMocks
    private TurmaServiceImpl service;

    @Mock
    private TurmaRepository repository;

    @Mock
    private TurmaMapper mapper;

    @Mock
    private Turma turma;

    @Mock
    private TurmaDTO dto;

    @Before
    public void setUp() {
        when(mapper.toDTO(turma)).thenReturn(dto);
        when(mapper.toEntity(dto)).thenReturn(turma);
    }

    @Test
    public void buscaPorId() {
        when(repository.findOne(any(Long.class))).thenReturn(turma);
        TurmaDTO dto = service.buscarPorCodigo(any(Long.class));
        assertEquals(true, Objects.nonNull(dto));
    }

    @Test
    public void count() {
        when(repository.quantidade(any(Integer.class))).thenReturn(2018);
        int ano  = service.count();
        assertEquals(2018, ano);
    }


}

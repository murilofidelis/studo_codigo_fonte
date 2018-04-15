package br.com.studo.service.impl;

import br.com.studo.domain.dto.DashbordDTO;
import br.com.studo.service.AlunoService;
import br.com.studo.service.DashbordService;
import br.com.studo.service.DisciplinaService;
import br.com.studo.service.ProfessorService;
import br.com.studo.service.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class DashbordServiceImpl implements DashbordService {

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private DisciplinaService disciplinaService;

    @Autowired
    private TurmaService turmaService;

    @Override
    public DashbordDTO montarDashBord() {
        DashbordDTO dashbord = new DashbordDTO();
        dashbord.setNumAlunos(alunoService.count());
        dashbord.setNumProfessores(professorService.count());
        dashbord.setNumDisciplinas(disciplinaService.count());
        dashbord.setNumTurmas(turmaService.count());
        return dashbord;
    }

}

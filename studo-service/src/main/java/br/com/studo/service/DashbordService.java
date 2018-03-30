package br.com.studo.service;

import br.com.studo.domain.dto.DashbordDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class DashbordService {

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private DisciplinaService disciplinaService;

    @Autowired
    private TurmaService turmaService;

    public DashbordDTO montarDashBord() {
        DashbordDTO dashbord = new DashbordDTO();
        dashbord.setNumAlunos(alunoService.cout());
        dashbord.setNumProfessores(professorService.cout());
        dashbord.setNumDisciplinas(disciplinaService.count());
        dashbord.setNumTurmas(turmaService.count());
        return dashbord;
    }

}

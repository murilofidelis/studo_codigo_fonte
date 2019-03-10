package br.com.studo.service.impl;

import br.com.studo.domain.dto.DashbordDTO;
import br.com.studo.domain.dto.DisciplinaDTO;
import br.com.studo.domain.dto.ProfessorDTO;
import br.com.studo.service.AlunoService;
import br.com.studo.service.DashbordService;
import br.com.studo.service.DisciplinaService;
import br.com.studo.service.ProfessorService;
import br.com.studo.service.TurmaService;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class    DashbordServiceImpl implements DashbordService {

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

    @Override
    public void gerarRelatotio(HttpServletResponse response) {
        try {
            InputStream inputStream = this.getClass().getResourceAsStream("/relatorios/resumo/resumo.jasper");
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(inputStream);

            InputStream subReport = this.getClass().getResourceAsStream("/relatorios/resumo/sub_report.jasper");
            JasperReport jasperSubReport = (JasperReport) JRLoader.loadObject(subReport);

            InputStream inputReportDisciplina = this.getClass().getResourceAsStream("/relatorios/resumo/sub_report_disciplina.jasper");
            JasperReport jasperSubReportDisciplinas = (JasperReport) JRLoader.loadObject(inputReportDisciplina);

            List<ProfessorDTO> professores = professorService.listaTodos();
            List<DisciplinaDTO> disciplinas = (List<DisciplinaDTO>) disciplinaService.listar();

            JRBeanCollectionDataSource subReportProfessor = new JRBeanCollectionDataSource(professores);
            JRBeanCollectionDataSource subReportDisciplina = new JRBeanCollectionDataSource(disciplinas);

            Map<String, Object> parametros = new HashMap<>();

            if(!professores.isEmpty()){
                parametros.put("SUB_REPORT_PATH", jasperSubReport);
                parametros.put("SUB_REPORT_DATA", subReportProfessor);
            }

            if(!disciplinas.isEmpty()){
                parametros.put("SUB_REPORT_DISCIPLINA_PATH", jasperSubReportDisciplinas);
                parametros.put("SUB_REPORT_DISCIPLINA_DATA", subReportDisciplina);
            }

            setParametros(parametros);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, new JREmptyDataSource());

            response.setContentType("application/x-pdf");
            response.setHeader("Content-Disposition", "inline; filename=resumo.pdf");
            final OutputStream outputStream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

        } catch (JRException | IOException e) {
            log.error("Erro ao gerar relatóiro: ", e);
        }
    }

    private void setParametros(Map<String, Object> parametros) {
        DashbordDTO dashbord = this.montarDashBord();
        parametros.put("TOTAL_ALUNOS", dashbord.getNumAlunos());
        parametros.put("TOTAL_PROFESSORES", dashbord.getNumProfessores());
        parametros.put("TOTAL_DICIPLINAS", dashbord.getNumDisciplinas());
        parametros.put("TOTAL_TURMAS", dashbord.getNumTurmas());
    }

}

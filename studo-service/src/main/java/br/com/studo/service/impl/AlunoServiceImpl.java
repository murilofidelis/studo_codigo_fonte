package br.com.studo.service.impl;

import br.com.studo.domain.Aluno;
import br.com.studo.domain.dto.AlunoDTO;
import br.com.studo.domain.dto.AlunoRelatorioDTO;
import br.com.studo.domain.dto.MatriculaDTO;
import br.com.studo.domain.mapper.AlunoMapper;
import br.com.studo.domain.mapper.MatriculaMapper;
import br.com.studo.repository.AlunoRepository;
import br.com.studo.service.AlunoService;
import br.com.studo.service.MatriculaService;
import br.com.studo.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class AlunoServiceImpl implements AlunoService {

    @Autowired
    private AlunoRepository repository;

    @Autowired
    private AlunoMapper alunoMapper;

    @Autowired
    private MatriculaMapper matriculaMapper;

    @Autowired
    private MatriculaService matriculaService;

    @Autowired
    private UsuarioService usuarioService;

    public Page<Aluno> filtarPesquisar(String nome, Pageable pageable) {
        return repository.findByNomeStartingWithIgnoreCase(nome, pageable);
    }

    @Transactional
    public AlunoDTO salvarAluno(AlunoDTO alunoDTO) {
        Aluno aluno = alunoMapper.toEntity(alunoDTO);
        if (aluno.getCodigo() == null) {
            usuarioService.criaUsuarioAluno(aluno);
        } else {
            usuarioService.atualizaUsuarioAluno(aluno);
        }
        return alunoMapper.toDTO(repository.save(aluno));
    }

    public AlunoDTO buscaPorCodigo(Long codigo) {
        return alunoMapper.toDTO(repository.findOne(codigo));
    }

    @Transactional
    public MatriculaDTO salvaMatricula(MatriculaDTO matriculaDTO) {
        return matriculaMapper.toDTO(matriculaService.salvaMatricula(matriculaMapper.toEntity(matriculaDTO)));
    }

    public List<MatriculaDTO> buscaMatriculasPorAluno(Long codigo) {
        return matriculaMapper.listDTO(matriculaService.buscaMatriculasPorAluno(codigo));
    }

    @Transactional
    public void deletaMatriculaAluno(Long codMatricula) {
        matriculaService.deletaMatriculaAluno(codMatricula);
    }

    public boolean verificaCpfCadastrado(String cpf) {
        return usuarioService.cpfExiste(cpf);
    }

    public Integer count() {
        return repository.quantidade();
    }

    public void geraRelatorioAluno(HttpServletResponse response) {
        try {
            InputStream inputStream = this.getClass().getResourceAsStream("/relatorios/alunos/alunos.jrxml");

            JasperDesign jasperDesign = JRXmlLoader.load(inputStream);

            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

            Map<String, Object> parametros = new HashMap<>();

            List<AlunoRelatorioDTO> alunos = preparaDadosRelatorio();

            JRDataSource dataSource = new JRBeanCollectionDataSource(alunos);

            parametros.put("nome_sistema", "STUDO");

            parametros.put("datasource", dataSource);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, dataSource);

            response.setContentType("application/x-pdf");
            response.setHeader("Content-Disposition", "inline; filename=alunos.pdf");

            final OutputStream outputStream = response.getOutputStream();

            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

        } catch (JRException | IOException e) {
            log.error("Erro ao gerar relatóiro: ", e);
        }
    }


    private List<AlunoRelatorioDTO> preparaDadosRelatorio() {

        List<AlunoRelatorioDTO> dados = new ArrayList<>();

        List<Aluno> alunos = (List<Aluno>) repository.findAll();

        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        alunos.forEach(aluno -> {
            AlunoRelatorioDTO dto = new AlunoRelatorioDTO();
            dto.setCodigo(aluno.getCodigo());
            dto.setNome(aluno.getNome());
            dto.setCpf(aluno.getCpf());
            dto.setDataNascimento(aluno.getDataNascimento().format(formatador));
            dto.setSexo(aluno.getSexo().name());
            dto.setEmail(aluno.getEmail().getDscEmail());
            dto.setStatus(aluno.getStatus() ? "ATIVO" : "INATIVO");
            dados.add(dto);
        });
        return dados;
    }

}

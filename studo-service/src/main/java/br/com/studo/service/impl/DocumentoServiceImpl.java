package br.com.studo.service.impl;

import br.com.studo.domain.Aluno;
import br.com.studo.domain.Documento;
import br.com.studo.domain.dto.DocumentoDTO;
import br.com.studo.exception.StudoException;
import br.com.studo.repository.DocumentoRepository;
import br.com.studo.service.DocumentoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Slf4j
@Service
public class DocumentoServiceImpl implements DocumentoService {

    @Autowired
    private DocumentoRepository repository;

    @Override
    public List<DocumentoDTO> listaPorAluno(Long codigo) {
        return repository.findByAluno(codigo);
    }

    @Override
    @Transactional
    public void upload(MultipartFile[] documentos, Long codigoAluno) {
        try {
            for (MultipartFile documento : documentos) {
                String nomeArquivo = documento.getOriginalFilename();
                byte[] bytesDocumento = documento.getBytes();
                salvar(new Documento(null, preparaAluno(codigoAluno), nomeArquivo, bytesDocumento));
            }
        } catch (IOException e) {
            log.error("ERRO AO SALVAR AQUIVOS: ", e);
        }
    }

    @Override
    public void download(HttpServletResponse response, Long id) {
        createResponse(response, getDocumento(id));
    }

    private void createResponse(HttpServletResponse response, Documento documento) {
        try (InputStream inputStream = new ByteArrayInputStream(documento.getDocumento())) {
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=" + documento.getNome());
            response.setHeader("Content-Length", String.valueOf(documento.getDocumento().length));
            FileCopyUtils.copy(inputStream, response.getOutputStream());
        } catch (IOException e) {
            log.info("ERRO AO REALIZAR DOWNLOAD: ", e.getMessage(), e);
            throw new StudoException("Erro ao realizar download do arquivo: ", e);
        }
    }

    @Transactional
    private void salvar(Documento documento) {
        repository.save(documento);
    }

    private Documento getDocumento(Long id) {
        return repository.findOne(id);
    }

    private Aluno preparaAluno(Long codigo) {
        Aluno aluno = new Aluno();
        aluno.setCodigo(codigo);
        return aluno;
    }
}

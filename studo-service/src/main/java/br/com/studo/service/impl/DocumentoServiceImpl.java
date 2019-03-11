package br.com.studo.service.impl;

import br.com.studo.config.StudoProperty;
import br.com.studo.domain.Aluno;
import br.com.studo.domain.Documento;
import br.com.studo.domain.dto.DocumentoDTO;
import br.com.studo.domain.mapper.DocumentoMapper;
import br.com.studo.exception.StudoException;
import br.com.studo.repository.DocumentoRepository;
import br.com.studo.service.DocumentoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DocumentoServiceImpl implements DocumentoService {

    private Path pathLocation;

    private final StudoProperty property;
    private final DocumentoMapper mapper;
    private final DocumentoRepository repository;

    @PostConstruct
    public void defineUrlDiretorio() {
        this.pathLocation = Paths.get(property.getArmazenamento().getAnexos());
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(pathLocation);
        } catch (IOException e) {
            log.error("Nao foi possivel configurar armazenamento de arquivos: ", e);
        }
    }

    @Override
    public List<DocumentoDTO> listaPorAluno(Long codigo) {
        return mapper.listDTO(repository.findByAlunoCodigo(codigo));
    }

    @Override
    @Transactional
    public void upload(List<DocumentoDTO> documentos) {
        try {
            for (DocumentoDTO dto : documentos) {
                if (dto.getCodigo() == null) {
                    String path = defineCaminho(dto);
                    this.salvaRegistro(dto, path);
                    byte[] bytesAnexo = Base64.getDecoder().decode(dto.getAnexoBase64());
                    this.criaPastaPorAluno(Paths.get(property.getArmazenamento().getAnexos() + "/" + dto.getCodigoAluno()));
                    try (FileOutputStream fos = new FileOutputStream(path)) {
                        fos.write(bytesAnexo);
                    }
                }
            }
        } catch (IOException e) {
            log.error("Erro ao salvar aquivos: {}", e);
        }
    }

    private void salvaRegistro(DocumentoDTO dto, String path) {
        Documento documento = mapper.toEntity(dto);
        documento.setAluno(preparaAluno(dto.getCodigoAluno()));
        documento.setCaminho(path);
        repository.save(documento);
    }

    private String defineCaminho(DocumentoDTO dto) {
        return new StringBuilder(property.getArmazenamento().getAnexos())
                .append("/")
                .append(dto.getCodigoAluno())
                .append("/")
                .append(dto.getNome())
                .append(dto.getExtensao())
                .toString();
    }

    private void criaPastaPorAluno(Path path) {
        try {
            if (!path.toFile().exists()) {
                Files.createDirectories(path);
            }
        } catch (IOException e) {
            log.error("Erro ao criar a pasta de aluno: " + path.toString(), e);
        }
    }

    @Override
    public void download(String caminho, HttpServletResponse response) {
        try (InputStream inputStream = new FileInputStream(new File(caminho))) {
            response.setContentType("application/octet-stream");
            FileCopyUtils.copy(inputStream, response.getOutputStream());
        } catch (IOException e) {
            log.error("Erro ao realizar download: {}", e);
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Documento documento = repository.findOne(id);
        if (documento == null) {
            throw new StudoException("Documento não econtrado!");
        }
        repository.delete(id);
        try {
            Files.delete(Paths.get(documento.getCaminho()));
        } catch (IOException e) {
            throw new StudoException("Erro ao exluir documento", e);
        }
    }

    private Aluno preparaAluno(Long codigo) {
        Aluno aluno = new Aluno();
        aluno.setCodigo(codigo);
        return aluno;
    }
}

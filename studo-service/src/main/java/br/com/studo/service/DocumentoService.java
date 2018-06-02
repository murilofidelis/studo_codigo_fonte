package br.com.studo.service;

import br.com.studo.domain.dto.DocumentoDTO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface DocumentoService {

    List<DocumentoDTO>listaPorAluno(Long codigo);

    void upload(MultipartFile[] documentos, Long codigoAluno);

    void download(HttpServletResponse response, Long codigoDocumento);
}

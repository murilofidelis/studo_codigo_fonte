package br.com.studo.service;

import br.com.studo.domain.dto.DocumentoDTO;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface DocumentoService {

    void init();

    List<DocumentoDTO> listaPorAluno(Long codigo);

    void upload(List<DocumentoDTO> documentos);

    void download(String caminho, HttpServletResponse response);

    void delete(Long id);
}

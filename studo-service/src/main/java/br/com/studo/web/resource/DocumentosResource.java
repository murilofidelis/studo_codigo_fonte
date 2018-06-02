package br.com.studo.web.resource;

import br.com.studo.domain.dto.DocumentoDTO;
import br.com.studo.service.DocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("documentos")
public class DocumentosResource {

    @Autowired
    private DocumentoService documentoService;

    @GetMapping("/listaPorAluno/{codigo}")
    public ResponseEntity<List<DocumentoDTO>> listaPorAluno(@PathVariable Long codigo) {
        List<DocumentoDTO> documentos = documentoService.listaPorAluno(codigo);
        return !documentos.isEmpty() ? ResponseEntity.ok(documentos) : ResponseEntity.notFound().build();
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(path = "/upload/{codigoAluno}", consumes = {"multipart/form-data"})
    public void uploadDocumento(@RequestPart("documentos[]") MultipartFile[] documentos, @PathVariable Long codigoAluno) {
        documentoService.upload(documentos, codigoAluno);
    }

    @ResponseBody
    @GetMapping("/download/{codigo}")
    public void download(HttpServletResponse response, @PathVariable Long codigo) {
        documentoService.download(response, codigo);
    }
}

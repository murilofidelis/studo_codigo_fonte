package br.com.studo.web.resource;

import br.com.studo.domain.dto.DocumentoDTO;
import br.com.studo.service.DocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("documentos")
public class DocumentosResource {

    @Autowired
    private DocumentoService service;

    @GetMapping("/listaPorAluno/{codigo}")
    public ResponseEntity<List<DocumentoDTO>> listaPorAluno(@PathVariable Long codigo) {
        List<DocumentoDTO> documentos = service.listaPorAluno(codigo);
        return !documentos.isEmpty() ? ResponseEntity.ok(documentos) : ResponseEntity.notFound().build();
    }

    @PostMapping("/upload")
    public void uploadDocumento(@RequestBody List<DocumentoDTO> documentos) {
        service.upload(documentos);
    }

    @ResponseBody
    @GetMapping("/download")
    public void download(@RequestParam("caminho") String caminho, HttpServletResponse response) {
        service.download(caminho, response);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

package br.com.studo.domain.mapper;

import br.com.studo.domain.Documento;
import br.com.studo.domain.dto.DocumentoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DocumentoMapper extends AbstractMapper<Documento, DocumentoDTO> {
}

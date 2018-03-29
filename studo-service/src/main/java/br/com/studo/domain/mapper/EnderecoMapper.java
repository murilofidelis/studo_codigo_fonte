package br.com.studo.domain.mapper;

import br.com.studo.domain.Endereco;
import br.com.studo.domain.dto.EnderecoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnderecoMapper extends AbstractMapper<Endereco, EnderecoDTO> {
}

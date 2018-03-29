package br.com.studo.domain.mapper;

import br.com.studo.domain.Email;
import br.com.studo.domain.dto.EmailDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmailMapper extends AbstractMapper<Email, EmailDTO> {
}

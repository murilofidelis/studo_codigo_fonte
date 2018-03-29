package br.com.studo.domain.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;

import java.util.List;

public interface AbstractMapper<Entity, DTO> {

    Entity toEntity(DTO dto);

    @InheritInverseConfiguration
    DTO toDTO(Entity entity);

    @IterableMapping(qualifiedByName = "toDTO")
    Iterable<DTO> iterable(Iterable<Entity> entities);

    @IterableMapping(qualifiedByName = "toDTO")
    List<DTO> listDTO(List<Entity> entities);

    @IterableMapping(qualifiedByName = "toEntity")
    List<Entity> listEntitys(List<DTO> dtos);

}

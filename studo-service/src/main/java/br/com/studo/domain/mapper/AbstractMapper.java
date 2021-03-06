package br.com.studo.domain.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;

import java.util.List;

public interface AbstractMapper<T, D> {

    T toEntity(D dto);

    @InheritInverseConfiguration
    D toDTO(T entity);

    @IterableMapping(qualifiedByName = "toDTO")
    Iterable<D> iterable(Iterable<T> entities);

    @IterableMapping(qualifiedByName = "toDTO")
    List<D> listDTO(List<T> entities);

    @IterableMapping(qualifiedByName = "toEntity")
    List<T> listEntitys(List<D> dtos);

}

package com.exo.webfluxdemo.mapper;

import com.exo.webfluxdemo.dto.PositionDto;
import com.exo.webfluxdemo.entity.PositionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PositionMapper {

  PositionDto toDto(PositionEntity position);
}

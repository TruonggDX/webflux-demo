package com.exo.webfluxdemo.mapper;

import com.exo.webfluxdemo.dto.PositionDto;
import com.exo.webfluxdemo.entity.PositionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PositionMapper {

  PositionDto toDto(PositionEntity position);

  PositionEntity toEntity(PositionDto dto);

  void update(PositionDto positionDto, @MappingTarget PositionEntity positionEntity);
}

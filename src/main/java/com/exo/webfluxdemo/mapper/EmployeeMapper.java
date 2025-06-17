package com.exo.webfluxdemo.mapper;

import com.exo.webfluxdemo.dto.EmployeeDto;
import com.exo.webfluxdemo.entity.EmployeeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

  EmployeeDto toDto(EmployeeEntity entity);

  EmployeeEntity toEntity(EmployeeDto dto);

  void update(EmployeeDto employeeDto, @MappingTarget EmployeeEntity employeeEntity);
}

package com.exo.webfluxdemo.dto;

import lombok.Data;

@Data
public class EmployeeDto {
  private Long id;
  private String name;
  private String address;
  private PositionDto positionDto;
}

package com.exo.webfluxdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "position")
public class PositionEntity {

  @Id
  private Long id;
  private String code;
  private String name;
  private Boolean active = false;
}

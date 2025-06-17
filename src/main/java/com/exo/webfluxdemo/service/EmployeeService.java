package com.exo.webfluxdemo.service;

import com.exo.webfluxdemo.dto.EmployeeDto;
import com.exo.webfluxdemo.dto.response.IdResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeService {

  Flux<EmployeeDto> getAllEmployees();

  Mono<EmployeeDto> getEmployee(Long id);

  Mono<IdResponse> addEmployee(EmployeeDto employee);

  Mono<IdResponse> deleteEmployee(Long id);

  Mono<IdResponse> updateEmployee(Long id, EmployeeDto employee);
}

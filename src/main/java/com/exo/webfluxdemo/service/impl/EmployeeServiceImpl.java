package com.exo.webfluxdemo.service.impl;

import com.exo.webfluxdemo.dto.EmployeeDto;
import com.exo.webfluxdemo.dto.response.IdResponse;
import com.exo.webfluxdemo.entity.EmployeeEntity;
import com.exo.webfluxdemo.exception.NotFoundException;
import com.exo.webfluxdemo.mapper.EmployeeMapper;
import com.exo.webfluxdemo.mapper.PositionMapper;
import com.exo.webfluxdemo.repository.EmployeeRepository;
import com.exo.webfluxdemo.repository.PositionRepository;
import com.exo.webfluxdemo.service.EmployeeService;
import com.exo.webfluxdemo.utils.MessageContants;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

  private final EmployeeRepository employeeRepository;
  private final PositionRepository positionRepository;
  private final EmployeeMapper employeeMapper;

  @Override
  public Flux<EmployeeDto> getAllEmployees() {
    return employeeRepository.findAll().map(employeeMapper::toDto);
  }

  @Override
  public Mono<EmployeeDto> getEmployee(Long id) {
    return employeeRepository.findById(id)
        .switchIfEmpty(Mono.error(new NotFoundException(MessageContants.NOT_FOUND_EMPLOYEE)))
        .map(employeeMapper::toDto);
  }

  @Override
  public Mono<IdResponse> addEmployee(EmployeeDto employee) {
    return positionRepository.findById(employee.getPositionDto().getId()).flatMap(p -> {
      EmployeeEntity entity = employeeMapper.toEntity(employee);
      entity.setPositionId(p.getId());
      return employeeRepository.save(entity).map(e -> IdResponse.builder().id(e.getId()).build());
    });
  }

  @Override
  public Mono<IdResponse> deleteEmployee(Long id) {
    return employeeRepository.findById(id)
        .switchIfEmpty(Mono.error(new NotFoundException(MessageContants.NOT_FOUND_EMPLOYEE)))
        .flatMap(entity -> employeeRepository.deleteById(id)
            .thenReturn(IdResponse.builder().id(entity.getId()).build()));
  }

  @Override
  public Mono<IdResponse> updateEmployee(Long id, EmployeeDto employee) {
    return employeeRepository.findById(id)
        .switchIfEmpty(Mono.error(new NotFoundException(MessageContants.NOT_FOUND_EMPLOYEE)))
        .flatMap(entity -> {
          employeeMapper.update(employee, entity);
          if (Objects.isNull(entity.getPositionId())) {
            return Mono.error(new NotFoundException(MessageContants.NOT_FOUND_POSITION));
          }
          entity.setPositionId(employee.getPositionDto().getId());
          return employeeRepository.save(entity)
              .map(e -> IdResponse.builder().id(e.getId()).build());
        });
  }
}

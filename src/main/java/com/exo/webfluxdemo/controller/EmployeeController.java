package com.exo.webfluxdemo.controller;

import com.exo.webfluxdemo.dto.EmployeeDto;
import com.exo.webfluxdemo.dto.response.IdResponse;
import com.exo.webfluxdemo.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/employee")
public class EmployeeController {

  private final EmployeeService employeeService;

  @GetMapping("/list")
  public Flux<EmployeeDto> list() {
    return employeeService.getAllEmployees();
  }

  @GetMapping("/{id}")
  public Mono<EmployeeDto> getEmployee(@PathVariable Long id) {
    return employeeService.getEmployee(id);
  }

  @PostMapping()
  public Mono<IdResponse> createEmployee(@RequestBody EmployeeDto employee) {
    return employeeService.addEmployee(employee);
  }

  @DeleteMapping("/{id}")
  public Mono<IdResponse> deleteEmployee(@PathVariable Long id) {
    return employeeService.deleteEmployee(id);
  }

  @PutMapping("/{id}")
  public Mono<IdResponse> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDto employee) {
    return employeeService.updateEmployee(id, employee);
  }
}

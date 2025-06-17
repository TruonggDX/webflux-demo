package com.exo.webfluxdemo.repository;

import com.exo.webfluxdemo.entity.EmployeeEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface EmployeeRepository extends ReactiveCrudRepository<EmployeeEntity, Long> {

}

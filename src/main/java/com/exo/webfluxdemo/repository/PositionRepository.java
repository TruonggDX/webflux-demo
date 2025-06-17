package com.exo.webfluxdemo.repository;

import com.exo.webfluxdemo.entity.PositionEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface PositionRepository extends ReactiveCrudRepository<PositionEntity, Long> {

}

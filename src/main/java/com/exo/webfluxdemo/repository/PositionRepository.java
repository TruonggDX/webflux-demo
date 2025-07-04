package com.exo.webfluxdemo.repository;

import com.exo.webfluxdemo.entity.PositionEntity;
import com.exo.webfluxdemo.repository.repo.PositionRepo;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface PositionRepository extends ReactiveCrudRepository<PositionEntity, Long>,
    PositionRepo {

  @Query("SELECT * FROM position WHERE active = false")
  Flux<PositionEntity> findAllActive();

}

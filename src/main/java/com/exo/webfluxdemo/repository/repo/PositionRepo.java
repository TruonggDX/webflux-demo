package com.exo.webfluxdemo.repository.repo;

import com.exo.webfluxdemo.entity.PositionEntity;
import reactor.core.publisher.Flux;

public interface PositionRepo {

  Flux<PositionEntity> searchByCodeOrName(String keyword);
}

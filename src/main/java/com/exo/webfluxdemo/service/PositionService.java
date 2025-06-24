package com.exo.webfluxdemo.service;

import com.exo.webfluxdemo.dto.PositionDto;
import com.exo.webfluxdemo.dto.response.IdResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PositionService {

  Flux<PositionDto> getPositions();

  Mono<PositionDto> getPosition(Long id);

  Mono<IdResponse> createPosition(PositionDto positionDto);

  Mono<IdResponse> updatePosition(Long id, PositionDto positionDto);

  Mono<IdResponse> deletePosition(Long id);

  Flux<PositionDto> searchPositions(String search);
}

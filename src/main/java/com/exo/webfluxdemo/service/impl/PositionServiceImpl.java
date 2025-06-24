package com.exo.webfluxdemo.service.impl;

import com.exo.webfluxdemo.dto.PositionDto;
import com.exo.webfluxdemo.dto.response.IdResponse;
import com.exo.webfluxdemo.exception.NotFoundException;
import com.exo.webfluxdemo.mapper.PositionMapper;
import com.exo.webfluxdemo.repository.PositionRepository;
import com.exo.webfluxdemo.service.PositionService;
import com.exo.webfluxdemo.utils.MessageContants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional
@RequiredArgsConstructor
public class PositionServiceImpl implements PositionService {

  private final PositionRepository positionRepository;
  private final PositionMapper positionMapper;

  @Override
  public Flux<PositionDto> getPositions() {
    return positionRepository.findAllActive().map(positionMapper::toDto);
  }

  @Override
  public Mono<PositionDto> getPosition(Long id) {
    return positionRepository.findById(id).switchIfEmpty(Mono.error(new NotFoundException(
        MessageContants.NOT_FOUND_POSITION))).map(positionMapper::toDto);
  }

  @Override
  @Transactional(readOnly = false)
  public Mono<IdResponse> createPosition(PositionDto positionDto) {
    return positionRepository.save(positionMapper.toEntity(positionDto))
        .map(entity -> IdResponse.builder().id(entity.getId()).build());
  }

  @Override
  @Transactional(readOnly = false)
  public Mono<IdResponse> updatePosition(Long id, PositionDto positionDto) {
    return positionRepository.findById(id)
        .switchIfEmpty(Mono.error(new NotFoundException(MessageContants.NOT_FOUND_POSITION)))
        .flatMap(positionEntity -> {
          positionMapper.update(positionDto, positionEntity);
          return positionRepository.save(positionEntity)
              .map(e -> IdResponse.builder().id(e.getId()).build());
        });
  }

  @Override
  @Transactional(readOnly = false)
  public Mono<IdResponse> deletePosition(Long id) {
    return positionRepository.findById(id)
        .switchIfEmpty(Mono.error(new NotFoundException(MessageContants.NOT_FOUND_POSITION)))
        .flatMap(e ->
            positionRepository.deleteById(id).thenReturn(IdResponse.builder().id(e.getId()).build())
        );
  }

  @Override
  public Flux<PositionDto> searchPositions(String search) {
    return positionRepository.searchByCodeOrName(search).map(positionMapper::toDto);
  }
}

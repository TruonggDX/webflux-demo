package com.exo.webfluxdemo.controller;

import com.exo.webfluxdemo.dto.PositionDto;
import com.exo.webfluxdemo.dto.response.IdResponse;
import com.exo.webfluxdemo.service.PositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/position")
public class PositionController {

  private final PositionService positionService;

  @GetMapping("/list")
  public Flux<PositionDto> list() {
    return positionService.getPositions();
  }

  @GetMapping("/{id}")
  public Mono<PositionDto> getPosition(@PathVariable Long id) {
    return positionService.getPosition(id);
  }

  @PostMapping()
  public Mono<IdResponse> createPosition(@RequestBody PositionDto positionDto) {
    return positionService.createPosition(positionDto);
  }

  @DeleteMapping("/{id}")
  public Mono<IdResponse> deletePosition(@PathVariable Long id) {
    return positionService.deletePosition(id);
  }

  @PutMapping("/{id}")
  public Mono<IdResponse> updatePosition(@PathVariable Long id, @RequestBody PositionDto dto) {
    return positionService.updatePosition(id, dto);
  }

  @GetMapping("/search")
  public Flux<PositionDto> searchPosition(@RequestParam String keyword) {
    return positionService.searchPositions(keyword);
  }
}

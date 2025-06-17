package com.exo.webfluxdemo.exception;

import java.time.LocalDateTime;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(NotFoundException.class)
  public Mono<Map<String, Object>> handleNotFound(NotFoundException ex,
      ServerWebExchange exchange) {
    exchange.getResponse().setStatusCode(HttpStatus.NOT_FOUND);
    return Mono.just(Map.of(
        "timestamp", LocalDateTime.now(),
        "status", 404,
        "error", "Not Found",
        "message", ex.getMessage(),
        "path", exchange.getRequest().getPath().toString()
    ));
  }
}
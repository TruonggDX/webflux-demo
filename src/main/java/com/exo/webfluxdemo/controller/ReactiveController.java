package com.exo.webfluxdemo.controller;

import java.time.LocalTime;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class ReactiveController {

  //demo khi gui 100 req
  // seq 1 100 | xargs -n1 -P100 curl -s http://localhost:8080/api/non-blocking
  // result : tốn khoảng 16 thread (reactor-http-nio-1 → nio-16) mà vẫn xử lý dược 100 req
  @GetMapping("/non-blocking")
  public Mono<String> nonBlocking() {
    return Mono.fromSupplier(() -> {
      return "✅ Responded at " + LocalTime.now() +
          " by thread: " + Thread.currentThread().getName();
    });
  }
}

package nz.net.osnz.demos;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * @author Kefeng Deng (https://bit.ly/2JFoCO1)
 */
@SpringBootApplication
@EnableWebFlux
public class Application {

  @Component
  @Log4j2
  public static class Handler {
    public Mono<ServerResponse> mono(ServerRequest request) {
      log.info("received a request for handler.mono");
      return ServerResponse
        .ok()
        .contentType(MediaType.TEXT_PLAIN)
        .body(BodyInserters.fromObject("MONO"));
    }

    public Mono<ServerResponse> flux(ServerRequest request) {
      log.info("received a request for handler.flux");
      return ServerResponse
        .ok()
        .contentType(MediaType.TEXT_PLAIN)
        .body(BodyInserters.fromPublisher(Flux.just("F", "L", "U", "X"), String.class));
    }
  }

  @RestController
  @RequestMapping("/controller")
  public static class HelloController {

    @GetMapping("/mono")
    public Mono<String> mono() {
      return Mono.just("mono");
    }

    @GetMapping("/flux")
    public Flux<String> flux() {
      return Flux.fromIterable(Arrays.asList("f", "l", "u", "x"));
    }

  }

//  @RestController
//  @RequestMapping("/")
//  @Slf4j
//  public static class SlacKController {
//
//    @PostMapping(path = "/", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity slackPost(@PostForm String body) {
//      log.info(body.toString());
//      return ResponseEntity.ok("ok");
//    }
//
//  }


  @Bean
  public RouterFunction<ServerResponse> monoRouterFunction(Handler handler) {
    return route(GET("/handler/mono"), handler::mono)
      .andRoute(GET("/handler/flux"), handler::flux);
  }

  public static void main(String[] args) {
    SpringApplication.run(Application.class);
  }

}

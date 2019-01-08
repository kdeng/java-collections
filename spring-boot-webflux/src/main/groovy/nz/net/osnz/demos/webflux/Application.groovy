package nz.net.osnz.demos.webflux

import groovy.transform.CompileStatic
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.Banner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.http.MediaType
import org.springframework.http.server.reactive.HttpHandler
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.reactive.config.EnableWebFlux
import org.springframework.web.server.adapter.WebHttpHandlerBuilder
import reactor.core.publisher.Mono
import reactor.netty.http.server.HttpServer

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@SpringBootApplication
@Controller
@CompileStatic
@EnableWebFlux
@Configuration
class Application {

    static final Logger LOG = LoggerFactory.getLogger(Application)

    static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application)
        app.bannerMode = Banner.Mode.OFF
        app.run(args)
    }

    @GetMapping(path = "/")
    @ResponseBody
    String index() {
        return "home"
    }

    @GetMapping(path = "/hello/{message}", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    @ResponseBody
    Mono<String> sayHello(@PathVariable String message) {
        LOG.info("Should say hello {}", message)
        return Mono.just("Hello world")
    }

    @Profile("default")
    @Bean
    HttpServer nettyContext(ApplicationContext context) {
        HttpHandler handler = WebHttpHandlerBuilder.applicationContext(context).build()
        ReactorHttpHandlerAdapter adapter = new ReactorHttpHandlerAdapter(handler)
        return HttpServer.create().host("localhost").port(8080).handle(adapter)
    }

}

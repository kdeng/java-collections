package nz.net.osnz.controller;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import io.micronaut.validation.Validated;
import io.reactivex.Single;
import nz.net.osnz.services.HelloService;

import javax.inject.Inject;
import javax.validation.constraints.NotBlank;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@Controller("/")
@Validated
public class HelloController {

    @Inject
    private HelloService helloService;

    @Get("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    public Single<String> helloWorld() {
        return Single.just("Hello world!");
    }

    @Get("/hello/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public Single<String> hello(@NotBlank String name) {
        return Single.just(helloService.hello(name));
    }

}

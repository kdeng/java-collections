package io.osnz;

import lombok.extern.log4j.Log4j2;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.model.rest.RestParamType;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.MediaType;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@SpringBootApplication
@Log4j2
public class Application {

  static final String contextPath = "/camel";

  static final int serverPort = 9091;

  public static void main(String[] args) {
    SpringApplication app = new SpringApplication(Application.class);
    app.setBannerMode(Banner.Mode.OFF);
    app.run(args);
    log.info("Server is ready ...");
  }

  @Component
  static class RestApi extends RouteBuilder {

    @Override
    public void configure() throws Exception {
//      CamelContext context = new DefaultCamelContext();

      restConfiguration()
        .contextPath(contextPath) //
        .port(serverPort)
        .enableCORS(true)
        .apiContextPath("/api-doc")
        .apiProperty("api.title", "Test REST API")
        .apiProperty("api.version", "v1")
        .apiProperty("cors", "true") // cross-site
        .apiContextRouteId("doc-api")
        .component("servlet")
        .bindingMode(RestBindingMode.json)
        .dataFormatProperty("prettyPrint", "true");

      rest("/api/").description("Teste REST Service")
//        .id("api-route")
        .post("/bean")
        .produces(MediaType.APPLICATION_JSON)
        .consumes(MediaType.APPLICATION_JSON)
//        .get("/hello/{place}")
        .bindingMode(RestBindingMode.auto)
        .type(Greeting.class)
        .enableCORS(true)
//        .outType(OutBean.class)
        .to("direct:remoteService")

        .get("/hello/{place}")
        .param().name("place").type(RestParamType.path).required(true).endParam()
        .to("direct:getService");

      from("direct:remoteService")
//        .routeId("direct-route")
        .tracing()
        .log(">>> ${body.id}")
        .log(">>> ${body.name}")
        .transform()
        .simple("Hello ${in.body.name}")
        .setHeader(Exchange.CONTENT_TYPE, constant(MediaType.TEXT_PLAIN))
        .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200));

      from("direct:getService")
        .tracing()
        .log(">>> ${header.place}")
        .process(new Processor() {
          @Override
          public void process(Exchange exchange) throws Exception {
            Greeting greeting = new Greeting();
            greeting.name = "Hello " + exchange.getIn().getHeader("place", String.class);
            greeting.id = 1000;
            exchange.getIn().setBody(greeting);
          }
        })
        .setHeader(Exchange.CONTENT_TYPE, constant(MediaType.APPLICATION_JSON))
        .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200));


    }
  }

}

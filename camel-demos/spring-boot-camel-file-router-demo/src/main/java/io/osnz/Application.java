package io.osnz;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.file.GenericFileMessage;
import org.apache.camel.model.ModelCamelContext;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.stereotype.Component;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@Slf4j
@SpringBootApplication
public class Application {

  @Autowired
  private CamelContext camelContext;

  public static void main(String[] args) throws Exception {
//    ModelCamelContext camelContext = new DefaultCamelContext();
//    camelContext.start();
//    camelContext.addRoutes(new FileRoute());
    // 为了保证主线程不退出
    new SpringApplicationBuilder(Application.class)
      .web(WebApplicationType.NONE)
      .run(args);
    log.info("Server is ready ...");
//    synchronized (Application.class) {
//      Application.class.wait();
//    }

  }

  @Component
  public static class FileRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
      from("file:/tmp/input?noop=true")
//      .tracing()
        .log(">> Processing ${id}")
        .log(LoggingLevel.DEBUG, LoggerFactory.getLogger(Application.class), "Processing ${id}")
        .process(new FileProcess())
//      .tracing()
        .log("${body}");
    }
  }

  @Slf4j
  public static class FileProcess implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
      GenericFileMessage message = (GenericFileMessage) exchange.getIn();
      log.info(message.getBody().toString());
      exchange.getMessage().setBody("Hello World");
    }
  }

}ß∂ƒß∂ßƒåß∂ƒß∂ƒfdssdfsdfsdf

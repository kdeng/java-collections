package io.osnz.demos.netty;

import cd.connect.openapi.support.ApiResponse;
import io.osnz.demos.netty.api.HelloClientService;
import io.osnz.demos.netty.api.HelloService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.Map;

/**
 * @author Kefeng Deng
 * @since 2019-05-13
 */
@Path("/proxy")
public class ProxyApi implements HelloService {

  private HelloClientService clientService;

//  @Inject
//  public ProxyApi(HelloClientService clientService) {
//    this.clientService = clientService;
//  }
  @Path("{name}")
  @Override
  public String hello(@PathParam("name") String name) {
    return "hello " + name;
  }

//  @Override
//  public String hello(String name, Map<String, String> extraHeaders) {
//    return null;
//  }
//
//  @Override
//  public ApiResponse<String> helloWithHttpInfo(String name) {
//    return null;
//  }

//  @GET
//  @Path("{name}")
//  public String proxyHello(@PathParam("name") String name) {
//    String helloName = name == null ? "[WORLD]" : "[" + name + "]";
//    return this.clientService.hello(helloName);
//  }


}

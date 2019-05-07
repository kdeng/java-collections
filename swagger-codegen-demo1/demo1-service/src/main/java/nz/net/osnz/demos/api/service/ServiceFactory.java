package nz.net.osnz.demos.api.service;

import org.glassfish.hk2.api.Immediate;

import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;

/**
 * @author Kefeng Deng
 * @since 2019-04-21
 */
@Immediate
public class ServiceFactory {

  private ServletContext context;

  public ServiceFactory(@Context ServletContext ctx) {
    this.context = ctx;
  }

  @Context
  public void setServletContext(ServletContext context) {
    System.out.println("servlet context set here");
    this.context = context;
    context.setInitParameter("CustomerApi.implementation", "nz.net.osnz.demos.api.CustomersService");
  }


}

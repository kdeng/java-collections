package io.osnz.demos.jersey.feature;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import java.io.IOException;

/**
 * @author Kefeng Deng (kefeng.deng@clearpoint.co.nz)
 */
public class DemoFilter implements ContainerRequestFilter {

  private static final Logger LOG = LoggerFactory.getLogger(DemoFilter.class);

  @Override
  public void filter(ContainerRequestContext requestContext) throws IOException {
    LOG.debug("Request filter");
  }

}

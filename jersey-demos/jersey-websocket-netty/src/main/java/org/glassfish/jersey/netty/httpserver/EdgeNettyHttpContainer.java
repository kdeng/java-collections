package org.glassfish.jersey.netty.httpserver;

import javax.ws.rs.core.Application;

/**
 * An extended http container to expose {@link NettyHttpContainer}.
 */
public class EdgeNettyHttpContainer extends NettyHttpContainer {

  public EdgeNettyHttpContainer(Application application) {
    super(application);
  }

}

package nz.net.osnz.demos.api;

import nz.net.osnz.demos.api.service.ServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CustomersService {

  private static final Logger log = LoggerFactory.getLogger(CustomersService.class);

  private ServiceFactory factory;

  @Inject
  public CustomersService(ServiceFactory factory) {
    this.factory = factory;
  }
}

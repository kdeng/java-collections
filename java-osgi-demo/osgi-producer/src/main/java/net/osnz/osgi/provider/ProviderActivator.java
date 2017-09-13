package net.osnz.osgi.provider;

import net.osnz.osgi.api.HelloWorldService;
import net.osnz.osgi.api.HelloWorldServiceImpl;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class ProviderActivator implements BundleActivator {

  private ServiceRegistration registration;


  @Override
  public void start(BundleContext bundleContext) throws Exception {
    registration = bundleContext.registerService(HelloWorldService.class.getName(), new HelloWorldServiceImpl(), null);
    System.out.println("Start HelloWorld");
  }

  @Override
  public void stop(BundleContext bundleContext) throws Exception {
    registration.unregister();
    System.out.println("Stop HelloWorld");
  }
}

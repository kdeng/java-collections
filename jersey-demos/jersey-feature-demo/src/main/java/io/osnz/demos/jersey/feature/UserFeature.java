package io.osnz.demos.jersey.feature;

import org.glassfish.hk2.api.JustInTimeInjectionResolver;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

import javax.ws.rs.core.Configurable;
import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;

public class UserFeature implements Feature {

  public static void applyConfig(Configurable<? extends Configurable> config) {
    config.register(new AbstractBinder() {
      @Override
      protected void configure() {
        bind(JustInTimeServiceResolver.class).to(JustInTimeInjectionResolver.class);
      }
    });
    config.register(UserResource.class);
    config.register(DemoFilter.class);

//    config.register(new UserBinder());
//    config.register(new InjectableBinder());
  }

  public boolean configure(FeatureContext context) {
    applyConfig(context);
    return true;
  }

}

package io.osnz.demos.jersey.feature;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import javax.inject.Singleton;

/**
 * @author Kefeng Deng
 * @since 2019-05-22
 */
public class UserBinder extends AbstractBinder {

  @Override
  protected void configure() {
    bind(UserRepository.class).to(UserRepository.class).in(Singleton.class);
    bind(UserService.class).to(UserService.class).in(Singleton.class);
  }

}

package io.osnz;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    auth
      .inMemoryAuthentication()
      .withUser("vip")
      .password(encoder.encode("vip"))
      .roles("VIP")
      .and()
      .withUser("user")
      .password(encoder.encode("user"))
      .roles("USER")
      .and()
      .withUser("admin")
      .password(encoder.encode("admin"))
      .roles("USER", "ADMIN");
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .anonymous().disable()
      .authorizeRequests()
        .anyRequest().authenticated()
      .and()
        .httpBasic();
  }


}

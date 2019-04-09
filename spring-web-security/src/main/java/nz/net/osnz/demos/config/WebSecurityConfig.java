package nz.net.osnz.demos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication()
      .withUser("user").password(passwordEncoder().encode("user")).roles("USER")
      .and()
      .withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .csrf().disable()
      .authorizeRequests()
        .antMatchers("/").permitAll()
        .antMatchers("/admin/**").hasRole("ADMIN")
        .antMatchers("/anonymous*").anonymous()
        .antMatchers("/login*").permitAll()
        .anyRequest().authenticated()
        .and()
      .formLogin()
//        .loginPage("/login")
        .loginProcessingUrl("/perform_login")
        .defaultSuccessUrl("/home", true)
        .failureUrl("/login_error")
//        .failureHandler(authenticationFailureHandler())
        .and()
      .logout()
//        .logoutUrl("/perform_logout")
        .deleteCookies("JSESSIONID")
//        .logoutSuccessHandler(logoutSuccessHandler())
        .logoutSuccessUrl("/logout_success")
        .and()
      .exceptionHandling()
        .accessDeniedPage("/access.html")
        .and()
      .headers()
        .defaultsDisabled()
        .frameOptions()
        .sameOrigin()
        .cacheControl();
  }

//  @Bean
//  @Override
//  public UserDetailsService userDetailsService() {
//    PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    UserDetails user = User.withUsername("admin")
//      .password(encoder.encode("password"))
//      .roles("USER")
//      .build();
//    return new InMemoryUserDetailsManager(user);
//  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}

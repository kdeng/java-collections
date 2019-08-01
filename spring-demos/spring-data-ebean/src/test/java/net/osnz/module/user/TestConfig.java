package net.osnz.module.user;

import net.osnz.module.user.repository.UserRepository;
import nz.net.osnz.common.ebean.EbeanConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;


/**
 * @author Kefeng Deng (deng@51any.com)
 */
@Configuration
@PropertySource("classpath:app-test-config.properties")
@Import(EbeanConfig.class)
public class TestConfig {

  @Bean
  public UserRepository userRepository() {
    return new UserRepository();
  }

  @Bean
  public DataSource dataSource() {
    // no need shutdown, EmbeddedDatabaseFactoryBean will take care of this
    EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
    return builder
      .setType(EmbeddedDatabaseType.H2) //.H2 or .DERBY
      .addScript("testdb/hsql-schema.sql")
      .addScript("testdb/hsql-data.sql")
      .build();
  }

}

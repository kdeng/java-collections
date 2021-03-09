package io.osnz;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@SpringBootApplication
@RestController
@EnableWebMvc
@Log4j2
@EnableTransactionManagement
public class Application {

  @Autowired
  private HelloService helloService;

  public static void main(String[] args) {
    SpringApplication app = new SpringApplication(Application.class);
    app.setBannerMode(Banner.Mode.OFF);
    app.run(args);
    log.info("Server is ready ...");
  }

//  @Bean(name = "entityManagerFactory")
//  public LocalContainerEntityManagerFactoryBean emf() {
//    LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
//    emf.setDataSource(dataSource);
//    emf.setPackagesToScan(new String[] {"your.package"});
//    emf.setJpaVendorAdapter(
//      new HibernateJpaVendorAdapter());
//    return emf;
//  }
//
//  @Bean(name = "transactionManager")
//  public PlatformTransactionManager transactionManager() {
//    JpaTransactionManager tm = new JpaTransactionManager();
//    tm.setEntityManagerFactory(emf);
//    tm.setDataSource(dataSource);
//    return tm;
//
//  }

  @GetMapping(path = "/{name}")
  public String index(@PathVariable("name") String name) {
    return helloService.sayHello(name);
  }

}

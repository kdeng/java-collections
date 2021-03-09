package io.osnz

import groovy.transform.CompileStatic
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.Banner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.config.annotation.EnableWebMvc

import javax.annotation.PostConstruct

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@SpringBootApplication
@RestController
@CompileStatic
@EnableWebMvc
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = 'io.osnz')
@EntityScan("io.osnz")
class Application {

  static final Logger LOG = LoggerFactory.getLogger(Application)

  @Autowired
  UserRepository userRepository;

  static void main(String[] args) {
    SpringApplication app = new SpringApplication(Application)
    app.bannerMode = Banner.Mode.OFF
    app.run(args)
  }

  @PostConstruct
  void initUsers() {
    LOG.info('Start to create some users')
    userRepository.saveAll(
      [
        [id: 1, name: 'aaa', email:'aaa@aaa'] as User,
        [id: 2, name: 'aaa', email:'bbb@aaa'] as User,
        [id: 3, name: 'aaa', email:'ccc@aaa'] as User,
        [id: 4, name: 'aaa', email:'ddd@aaa'] as User,
        [id: 4, name: 'aaa', email:'eee@aaa'] as User,
        [id: 5, name: 'fff', email:'fff@aaa'] as User,
        [id: 6, name: 'ggg', email:'ggg@aaa'] as User,
      ]
    )
  }

  @GetMapping(path = '/{name}')
  List<User> index(@PathVariable('name') String name) {
    return userRepository.findByName(name)
  }

}

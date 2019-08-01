package nz.net.osnz

import groovy.transform.CompileStatic
import org.apache.activemq.ActiveMQConnectionFactory
import org.apache.activemq.pool.PooledConnectionFactory
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.Banner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jms.core.JmsTemplate
import org.springframework.scheduling.annotation.EnableScheduling

import javax.jms.ConnectionFactory

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@SpringBootApplication
@CompileStatic
@EnableScheduling
@Configuration
class Application {

  static final Logger LOG = LoggerFactory.getLogger(Application)

  static final String BROKER_URL = 'tcp://localhost:61616'

  static final String DESTINATION = "test-topic"

  static final String TOPIC_DESTINATION = "topic.test"

  static void main(String[] args) {
    SpringApplication app = new SpringApplication(Application)
    app.bannerMode = Banner.Mode.OFF
    app.run(args)
  }

  @Bean
  PooledConnectionFactory pooledConnectionFactory() {
    return new PooledConnectionFactory(connectionFactory: new ActiveMQConnectionFactory(BROKER_URL))
  }

  @Autowired
  @Bean
  JmsTemplate jmsTemplate(ConnectionFactory connectionFactory) {
    return new JmsTemplate(connectionFactory: connectionFactory)
  }


}

package nz.net.osnz.demos.jms.rabbitmq

import com.rabbitmq.client.BuiltinExchangeType
import com.rabbitmq.client.Channel

import com.rabbitmq.client.ConnectionFactory
import com.sun.jndi.ldap.pool.PooledConnectionFactory
import groovy.transform.CompileStatic
import org.springframework.amqp.core.AmqpAdmin
import org.springframework.amqp.rabbit.annotation.EnableRabbit
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory
import org.springframework.amqp.rabbit.connection.Connection
import org.springframework.amqp.rabbit.core.RabbitAdmin
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.boot.Banner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.scheduling.annotation.EnableScheduling

import javax.annotation.PostConstruct

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@SpringBootApplication
@CompileStatic
@EnableScheduling
class Application {

    static final String EXCHANGE_NAME = "spring.rabbitmq.exchange"

    static final String TOPIC_NAME = "spring-boot-exchange"

    static final String QUEUE_NAME = "spring-boot-queue"

    static final String CONTRACT_FANOUT = "CONTRACT_FANOUT"

    static final String CONTRACT_TOPIC = "CONTRACT_TOPIC"

    static final String CONTRACT_DIRECT = "CONTRACT_DIRECT"

    static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application)
        app.bannerMode = Banner.Mode.OFF
        app.run(args)
    }

    CachingConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost")
        return connectionFactory
    }

    @PostConstruct
    void init() {
        CachingConnectionFactory connectionFactory = connectionFactory()
        Connection conn = connectionFactory.createConnection()
        Channel channel = conn.createChannel(false)
//        channel.queueDeclare(Application.QUEUE_NAME, false, false, false, null)
        channel.exchangeDeclare(Application.EXCHANGE_NAME, BuiltinExchangeType.TOPIC, false)
    }


    @Bean
    AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory())
    }

    @Bean
    RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory())
        return rabbitTemplate
    }


//    @Bean
//    Queue queue() {
//        return new Queue(queueName, false)
//    }
//
//    @Bean
//    TopicExchange exchange() {
//        return new TopicExchange(topicExchangeName);
//    }
//
//    @Bean
//    Binding binding(Queue queue, TopicExchange exchange) {
//        return BindingBuilder.bind(queue).to(exchange).with("foo.bar.#");
//    }
//
//    @Bean
//    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
//                                             MessageListenerAdapter listenerAdapter) {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
//        container.setQueueNames(queueName);
//        container.setMessageListener(listenerAdapter);
//        return container;
//    }
//
//
//    @Bean
//    MessageListenerAdapter listenerAdapter(Receiver receiver) {
//        return new MessageListenerAdapter(receiver, "receiveMessage");
//    }


}

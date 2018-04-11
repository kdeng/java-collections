package nz.net.osnz.demos.jms.rabbitmq.topic

import com.rabbitmq.client.BuiltinExchangeType
import com.rabbitmq.client.Channel
import nz.net.osnz.demos.jms.rabbitmq.Application
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory
import org.springframework.amqp.rabbit.connection.Connection
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

import javax.annotation.PostConstruct
import java.util.concurrent.atomic.AtomicInteger

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@Component
class Publisher {

    static final Logger LOG = LoggerFactory.getLogger(Publisher)

    @Autowired
    CachingConnectionFactory connectionFactory

    static final AtomicInteger counter = new AtomicInteger(0)

    Channel channel

    @PostConstruct
    void init() {
        LOG.info("Initialise Publisher")
        Connection connection = connectionFactory.createConnection()
        channel = connection.createChannel(false)
    }

    @Scheduled(fixedRate = 2000L)
    void publishMessage() {
        int idx = counter.incrementAndGet()
        String message = "Published #$idx message"
        LOG.info("=== Trying to publish message: {}", message)
        channel.basicPublish(Application.EXCHANGE_NAME, "routing.key.one", null, message.bytes)
    }

}

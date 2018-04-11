package nz.net.osnz.demos.jms.rabbitmq.topic

import com.rabbitmq.client.AMQP
import com.rabbitmq.client.Channel
import com.rabbitmq.client.Consumer
import com.rabbitmq.client.DefaultConsumer
import com.rabbitmq.client.Envelope
import nz.net.osnz.demos.jms.rabbitmq.Application
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory
import org.springframework.amqp.rabbit.connection.Connection
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import javax.annotation.PostConstruct

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@Component
class SubscriberTwo {

    static final Logger LOG = LoggerFactory.getLogger(SubscriberTwo)

    @Autowired
    CachingConnectionFactory connectionFactory

    Channel channel

    @PostConstruct
    void init() {
        LOG.info("Initialise subscriber two")
        Connection connection = connectionFactory.createConnection()
        channel = connection.createChannel(false)
        String queueName = channel.queueDeclare().queue
        channel.queueBind(queueName, Application.EXCHANGE_NAME, 'routing.key.two')
        LOG.info("Trying to binding basic consume")
        channel.basicConsume(queueName, false, 'routing.key.two', getMyConsumer(channel))

    }

    Consumer getMyConsumer(Channel ch) {
        new DefaultConsumer(ch) {
            @Override
            void handleDelivery(String consumerTag,
                                Envelope envelope,
                                AMQP.BasicProperties properties,
                                byte[] body)
                throws IOException {
                LOG.info("=== Received a message: {}", new String(body))
                long deliveryTag = envelope.deliveryTag
                ch.basicAck(deliveryTag, false)
            }
        }
    }
}

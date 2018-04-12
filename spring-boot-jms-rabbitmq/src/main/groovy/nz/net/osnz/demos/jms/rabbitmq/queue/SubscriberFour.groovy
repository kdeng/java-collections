package nz.net.osnz.demos.jms.rabbitmq.queue

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitHandler
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.stereotype.Component

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@Component
@RabbitListener(queues = ["spring-boot-hello-queue"])
@ConditionalOnBean(Sender)
class SubscriberFour {

    static final Logger LOG = LoggerFactory.getLogger(SubscriberFour)


    @RabbitHandler
    void processMessage(String message) {
        LOG.info("Received message: {}", message)
    }

}

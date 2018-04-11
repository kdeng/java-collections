package nz.net.osnz.demos.jms.rabbitmq.queue

import nz.net.osnz.demos.jms.rabbitmq.Application
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@Component
@ConditionalOnBean(Sender)
class Receiver {

    static final Logger LOG = LoggerFactory.getLogger(Receiver)

    @Autowired
    RabbitTemplate rabbitTemplate

    @Scheduled(fixedRate = 1000L)
    void receiveMessage() {
        LOG.info("### Trying to receive a message")
        String msg = new String(rabbitTemplate.receiveAndConvert(Application.QUEUE_NAME))
        LOG.info("### Received message : {}", msg)
    }


}

package nz.net.osnz.demos.jms.rabbitmq.queue

import nz.net.osnz.demos.jms.rabbitmq.Application
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.annotation.Order
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

import java.util.concurrent.atomic.AtomicInteger

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@Component
@Order(1)
class Sender {

    static final Logger LOG = LoggerFactory.getLogger(Sender)

    @Autowired
    RabbitTemplate rabbitTemplate

    private final AtomicInteger counter = new AtomicInteger()

    @Scheduled(fixedRate = 2000L)
    void run() {
        int idx = counter.incrementAndGet()
        String message = "#$idx Hello From RabbitMQ"
        LOG.info("### Sending message #{} : {}", idx, message)
        rabbitTemplate.convertAndSend(Application.QUEUE_NAME, message)
        rabbitTemplate.convertAndSend(Application.HELLO_QUEUE_NAME, message)
    }


}

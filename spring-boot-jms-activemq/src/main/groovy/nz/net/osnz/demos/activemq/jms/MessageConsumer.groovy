package nz.net.osnz.demos.activemq.jms

import nz.net.osnz.Application
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jms.core.JmsTemplate
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

import javax.jms.Message

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@Component
class MessageConsumer {
    static final Logger LOG = LoggerFactory.getLogger(MessageConsumer)

    @Autowired
    JmsTemplate jmsTemplate

    @Scheduled(fixedRate = 3000L)
    void consumeMessage() {
        Message message = jmsTemplate.receive(Application.DESTINATION)
        LOG.info("Receiving message: {}", message)
    }

}

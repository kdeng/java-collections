package nz.net.osnz.demos.activemq.jms

import nz.net.osnz.Application
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

import javax.annotation.PostConstruct
import javax.annotation.PreDestroy
import javax.jms.*
import java.util.concurrent.atomic.AtomicInteger

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@Component
class MessagePublisher {

    static final Logger LOG = LoggerFactory.getLogger(MessagePublisher)

    @Autowired
    ConnectionFactory connectionFactory

    MessageProducer producer

    Session session

    Connection connection

    AtomicInteger counter = new AtomicInteger(0)

    @PostConstruct
    void init() {
        connection = connectionFactory.createConnection()
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE)
        producer = session.createProducer(session.createTopic(Application.TOPIC_DESTINATION))
    }

    @Scheduled(fixedRate = 1500L)
    void publishMessage() {
        int idx = counter.incrementAndGet()
        producer.send(transformPayloadToMessage(idx + " : Published Hello World "))
        LOG.info("Published message : {}", idx)
    }

    private TextMessage transformPayloadToMessage(String payload) throws JMSException {
        return this.session.createTextMessage(payload)
    }

    @PreDestroy
    void destroy() {
        session.close()
        connection.close()
    }

}

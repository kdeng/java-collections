package nz.net.osnz.demos.activemq.jms

import nz.net.osnz.Application
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import javax.annotation.PostConstruct
import javax.annotation.PreDestroy
import javax.jms.*

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@Component
class MessageSubscriber {

    static final Logger LOG = LoggerFactory.getLogger(MessageSubscriber)

    @Autowired
    ConnectionFactory connectionFactory

    TopicSubscriber topicSubscriber
    Connection connection
    Session session

    @PostConstruct
    void init() {
        connection = connectionFactory.createConnection('admin', 'admin')
        connection.clientID = 'message-subscriber'
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE)
        topicSubscriber = session.createDurableSubscriber(session.createTopic(Application.TOPIC_DESTINATION),
            'test-topic-subscriber')

        topicSubscriber.messageListener = new MessageListener() {
            @Override
            void onMessage(Message message) {
                LOG.info("New message arrives: {}", message)
            }
        }

        connection.start()
    }

    @PreDestroy
    void destroy() {
        session.close()
        connection.close()
    }

}

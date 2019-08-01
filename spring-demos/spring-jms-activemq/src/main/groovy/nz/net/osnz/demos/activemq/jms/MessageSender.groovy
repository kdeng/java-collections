package nz.net.osnz.demos.activemq.jms

import groovy.transform.CompileStatic
import nz.net.osnz.Application
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jms.core.JmsTemplate
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

import java.util.concurrent.atomic.AtomicInteger

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@Component
@CompileStatic
class MessageSender {

  static final Logger LOG = LoggerFactory.getLogger(MessageSender)

  static final AtomicInteger COUNTER = new AtomicInteger(0)

  @Autowired
  JmsTemplate jmsTemplate

  @Scheduled(fixedRate = 2000L)
  void sendMessage() {
    int currentIdx = COUNTER.incrementAndGet()
    jmsTemplate.convertAndSend(Application.DESTINATION, currentIdx + " : Hell World")
    LOG.info("Start send message #{}", currentIdx)
  }
}

package nz.net.osnz

import nz.net.osnz.demos.jms.Application
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jms.core.JmsTemplate
import org.springframework.test.context.junit4.SpringRunner

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@RunWith(SpringRunner)
@SpringBootTest(classes = Application)
class ApplicationTest {

  @Autowired
  JmsTemplate jmsTemplate

  @Test
  void shouldEnableJmsTemplateProperly() {
    Assert.assertNotNull(jmsTemplate)
  }

}

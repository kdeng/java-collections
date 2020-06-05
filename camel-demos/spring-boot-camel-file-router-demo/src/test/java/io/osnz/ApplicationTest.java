package io.osnz;

import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.DisableJmx;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@CamelSpringBootTest
@ContextConfiguration
@DisableJmx(false)
public class ApplicationTest {

  @Autowired
  protected CamelContext camelContext;

  @EndpointInject(value = "mock:foo")
  protected MockEndpoint file;

  @Test
  public void testMocksAreValid() throws Exception {
    file.message(0).header("bar").isEqualTo("ABC");
    MockEndpoint.assertIsSatisfied(camelContext);
  }


}

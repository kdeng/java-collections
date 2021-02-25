package io.osnz;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.test.context.ActiveProfiles;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@ActiveProfiles("audit")
public class AspectTest {

  @Test
  public void shouldInvokePointcut() {
    LogService logService = Mockito.spy(LogService.class);
    HelloService helloService = new HelloService(logService);

    AspectJProxyFactory factory = new AspectJProxyFactory(helloService);
    AuditAspect aspect = new AuditAspect(logService);

    factory.addAspect(aspect);

    HelloService helloServiceProxy = factory.getProxy();
    String response = helloServiceProxy.hello("world");

    Assertions.assertThat(response).isEqualTo("Hello world");

    Mockito.verify(logService, Mockito.atLeastOnce()).printMessage(Mockito.any(Audit.Level.class), Mockito.anyString(), Mockito.any());
    Mockito.verify(logService, Mockito.atLeastOnce()).printMessage(Mockito.eq(Audit.Level.INFO), Mockito.anyString(), Mockito.any());
  }

}

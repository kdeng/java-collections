package nz.net.osnz.demos.j2ee.listener

import org.slf4j.Logger
import org.slf4j.LoggerFactory

import javax.servlet.ServletContextEvent
import javax.servlet.ServletContextListener
import javax.servlet.annotation.WebListener

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@WebListener
class MyServletContextListener implements ServletContextListener {
  private static final Logger LOG = LoggerFactory.getLogger(MyServletContextListener)

  @Override
  void contextInitialized(ServletContextEvent sce) {
    LOG.info("MyServletContextListener -> context initialized")
  }

  @Override
  void contextDestroyed(ServletContextEvent sce) {
    LOG.info("MyServletContextListener -> context destroyed")
  }

}

package nz.net.osnz.train.ws

import org.apache.cxf.transport.servlet.CXFServlet
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.embedded.ServletRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ImportResource

/**
 * @author Kefeng Deng (k.deng@auckland.ac.nz)
 */
@SpringBootApplication
@ImportResource("classpath:application-config.xml")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application, args)
	}

	@Bean
	public ServletRegistrationBean servletRegistrationBean() {
		CXFServlet cxfServlet = new CXFServlet();
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(cxfServlet, "/ws/*");
		servletRegistrationBean.setLoadOnStartup(1);
		return servletRegistrationBean;
	}

}

package nz.net.osnz.train

import nz.net.osnz.train.bean.JeeComponentsBeanFactoryPostProcessor
import nz.net.osnz.train.filter.MyFilter
import nz.net.osnz.train.servlet.MyServlet
import org.springframework.boot.Banner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory
import org.springframework.boot.context.embedded.FilterRegistrationBean
import org.springframework.boot.context.embedded.ServletRegistrationBean
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.core.env.Environment

import java.util.concurrent.TimeUnit

/**
 * Main class to start a JVM container
 *
 * @author Kefeng Deng
 */
@SpringBootApplication
class Application {

	public static void main(String[] args) {

		SpringApplication app = new SpringApplication(Application.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.setBanner(new Banner() {
			@Override
			void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {

			}
		})
		app.run(args);


//		ApplicationContext ctx = SpringApplication.run(Application.class, args)

//		System.out.println("Let's inspect the beans provided by Spring Boot:")
//
//		String[] beanNames = ctx.getBeanDefinitionNames()
//		Arrays.sort(beanNames)
//		beanNames?.each {
//			println it
//		}
	}

	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
		JettyEmbeddedServletContainerFactory factory = new JettyEmbeddedServletContainerFactory();
		factory.setPort(9000);
		factory.setSessionTimeout(10, TimeUnit.MINUTES);
//		factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/notfound.html"));
		return factory;
	}

	@Bean
	JeeComponentsBeanFactoryPostProcessor jeeComponentsBeanFactoryPostProcessor() {
		return new JeeComponentsBeanFactoryPostProcessor(Application.class.getPackage().getName());
	}

//	@Bean
//	public FilterRegistrationBean filterRegistration() {
//		FilterRegistrationBean registration = new FilterRegistrationBean(new MyFilter());
//		registration.setEnabled(true);
//		return registration;
//	}

//	@Bean
//	public ServletRegistrationBean servletRegistration() {
//		ServletRegistrationBean registrationBean = new ServletRegistrationBean(new MyServlet());
//		registrationBean.addUrlMappings('/home')
//		registrationBean.setEnabled(true)
//		return registrationBean
//	}

}

package nz.net.osnz.demos.j2ee

import nz.net.osnz.demos.j2ee.bean.JeeComponentsBeanFactoryPostProcessor
import org.springframework.boot.Banner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.core.env.Environment

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

package nz.net.osnz

import groovy.transform.CompileStatic
import nz.net.osnz.common.jsp.EnableJSPView
import nz.net.osnz.common.thymeleaf.EnableThymeleaf
import org.springframework.boot.Banner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@SpringBootApplication
@EnableAutoConfiguration
@Controller
@CompileStatic
@EnableJSPView
@EnableThymeleaf
@Configuration
class Application {

    static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application)
        app.bannerMode = Banner.Mode.OFF
        app.run(args)
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    @ResponseBody
    String index() {
        return "Welcome to Spring MVC template demo - JSP, thymeleaf"
    }

    @RequestMapping(path = "/jsp", method = RequestMethod.GET)
    String jsp() {
        return "home.jsp"
    }

    @RequestMapping(path = "/thymeleaf", method = RequestMethod.GET)
    String thymeleaf() {
        return "home.html"
    }

}

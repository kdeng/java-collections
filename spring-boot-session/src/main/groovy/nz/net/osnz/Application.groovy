package nz.net.osnz

import groovy.transform.CompileStatic
import org.springframework.boot.Banner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@SpringBootApplication
@EnableAutoConfiguration
@RestController
@Controller
@CompileStatic
public class Application {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application)
        app.bannerMode = Banner.Mode.OFF
        app.run(args)
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String index() {
        return 'Welcome to groovy sample'
    }

}

package nz.net.osnz.demo

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@SpringBootApplication
@Controller
open class Application {

    @RequestMapping("/")
    @ResponseBody
    fun home(): String {
        return "hello world"
    }


}

fun main(args: Array<String>) {

    SpringApplication.run(Application::class.java, *args)


}
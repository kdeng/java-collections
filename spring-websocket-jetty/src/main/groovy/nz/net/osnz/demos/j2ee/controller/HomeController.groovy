package nz.net.osnz.demos.j2ee.controller

import groovy.transform.CompileStatic
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.ModelAndView

@Controller
@CompileStatic
@RequestMapping(value='/')
class HomeController {
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView()
        mv.setViewName('home')
        return mv
    }

}

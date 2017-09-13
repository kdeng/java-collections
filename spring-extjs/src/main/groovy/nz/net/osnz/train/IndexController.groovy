package nz.net.osnz.train

import groovy.transform.CompileStatic
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.ModelAndView

/**
 * Created with IntelliJ IDEA.
 * User: kdeng
 * Date: 5/11/13
 * Time: 11:18 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@CompileStatic
@RequestMapping(value='/')
class IndexController {

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView()
        mv.setViewName('index')
        return mv
    }

}

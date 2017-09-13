package nz.net.osnz.practice.spring.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

/**
 *
 * @author Kefeng Deng (deng@51any.com)
 */
@Controller
@RequestMapping("/spring")
public class HelloController {

	@RequestMapping("")
	public String index(ModelMap modelMap) {
		modelMap.addAttribute("name", "Spring MVC World")
		return "index"
	}

	@RequestMapping("/hello")
	@ResponseBody
	public String hello() {
		return "Hello from Spring MVC as response body"
	}
}

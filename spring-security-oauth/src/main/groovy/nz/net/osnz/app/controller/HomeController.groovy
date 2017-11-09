package nz.net.osnz.app.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@Controller
class HomeController {

	@RequestMapping('/')
	public ModelAndView home() {
		ModelAndView model = new ModelAndView('index.html')
		model.addObject('title', 'hello world')
		return model
	}

	@GetMapping('/login')
	public ModelAndView login() {
		ModelAndView model = new ModelAndView('login.html')
		model.addObject('title', 'Login')
		return model
	}

	@GetMapping('/about')
	public ModelAndView about() {
		ModelAndView model = new ModelAndView('about.jsp')
		model.addObject('title', 'about us')
		return model
	}

	@GetMapping('/user')
	public ModelAndView user() {
		ModelAndView model = new ModelAndView('user.jsp')
		model.addObject('title', 'user page')
		return model
	}

	@GetMapping('/admin')
	public ModelAndView admin() {
		ModelAndView model = new ModelAndView('admin.jsp')
		model.addObject('title', 'admin page')
		return model
	}

	@GetMapping('/403')
	public ModelAndView accessDenied() {
		ModelAndView model = new ModelAndView('403.jsp')
		model.addObject('title', 'Access Denied')
		return model
	}


}

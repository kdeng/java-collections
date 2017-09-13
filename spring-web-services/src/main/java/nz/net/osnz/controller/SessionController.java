package nz.net.osnz.controller;

import nz.net.osnz.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.annotation.PostConstruct;

/**
 * @author Kefeng Deng
 */
@RestController
public class SessionController {

	@Autowired
	UserDto userDto;

	@PostConstruct
	public void validateUser() {
		assert userDto != null;
	}

	@RequestMapping(value = {"/", "/first"})
	@ResponseBody
	public String first() {
		return "Hello World: " + userDto.username;
	}

	@RequestMapping(value = "/second")
	@ResponseBody
	public String second() {
		userDto.username = "New Zealand";
		return "Hello World: " + userDto.username;
	}


}

package nz.net.osnz.controller;

import nz.net.osnz.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Kefeng Deng (deng@51any.com) - Created on 9/03/15.
 */
@Controller
@RequestMapping(value = "/cart")
public class CartController {


	@Autowired
	UserDto userDto;

	@RequestMapping(value = "/add/{itemId}")
	@ResponseBody
	public String addItem(@PathVariable("itemId") Long itemId) {
		userDto.putItem(itemId, 3);
		return userDto.toString();
	}

	@RequestMapping(value = "/display")
	@ResponseBody
	public String displayItem() {
		return userDto.toString();
	}

	@RequestMapping(value = "")
	public String list() {
		return "list";
	}


}

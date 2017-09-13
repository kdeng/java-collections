package net.osnz.learn.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("/admin/user")
public class UserController {

  @RequestMapping("/user/hello")
  public String userDetail(Model model) {
    model.addAttribute("value", "World");
    return "details";
  }

  @RequestMapping("/admin/user/hello")
  public String adminUserDetail(Model model) {
    model.addAttribute("value", "World");
    return "admin/details";
  }


}

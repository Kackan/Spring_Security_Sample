package com.myapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myapp.entity.User;
import com.myapp.service.UserService;

@Controller
@RequestMapping("/register")
public class RegisterController {

	@Autowired
	private UserService userService;
	
	
	@GetMapping("/showRegisterForm")
	public String showRegisterForm(Model model)
	{
		User user=new User();
		model.addAttribute(user);
		return "register-form";
	}
	
	
	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute("user") User user)
	{
		userService.save(user);
		return "register-confirmation";
	}
	
}

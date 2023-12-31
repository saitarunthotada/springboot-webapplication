package com.springboot.webapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.webapplication.model.User;
import com.springboot.webapplication.service.UserService;

@Controller
public class UserController {
	@Autowired
	private final UserService userService;
	private PasswordEncoder passwordEncoder;

	public UserController(UserService userService, PasswordEncoder passwordEncoder) {
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
	}

	@GetMapping("/user/register")
	public String index(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "user-register";
	}

	@PostMapping("/user/register")
	public String registerUser(@ModelAttribute("user") User user) {
		userService.registerUser(user);
		return "user-login";
	}

	@GetMapping("/user/login")
	public String login(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "user-login";
	}

	@PostMapping("user/login")
	public String viewUserDetails(@RequestParam String username, @RequestParam String password, Model model)
	{
		User user = userService.viewUserDetails(username);
		if(user!= null)
		{
			boolean passwordMatch = passwordEncoder.matches(password, user.getPassword());
			if(passwordMatch)
			{
				model.addAttribute("user", user);
				return "user-profile";
			}
			else
			{
				return "user-register";
			}
		}
		else{
			return "user-register";
		}
	}

	@GetMapping("/user/edit")
	public String showUpdateUserForm(Model model, @RequestParam("username") String username) {
		User user = userService.getUser(username);
		if (user != null) {
			model.addAttribute("user", user);
			return "user-update";
		} else {
			return "user-register";
		}
	}

	@PostMapping("/user/edit")
	public String updateUser(@ModelAttribute User updateUser, Model model) {
		User updatedUser = userService.updateUser(updateUser);
		if (updatedUser != null) {
			model.addAttribute("updatedUser", updatedUser);
			return "user-profile";
		} else {
			return "user-register";
		}
	}

}

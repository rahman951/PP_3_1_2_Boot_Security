package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.services.UserServices;
import ru.kata.spring.boot_security.demo.services.UserServicesImpl;

import java.util.List;

@Controller
public class UserController {
	private UserServices userService;
	
	@Autowired
	public UserController(UserServicesImpl userService) {
		this.userService = userService;
	}
	
	//Методы user
	@GetMapping("/user")
	public String userInfo(Model model, @AuthenticationPrincipal UserDetails userDetails) {
		User user = userService.findByUsername(userDetails.getUsername());
		model.addAttribute("user", user);
		return "user";
	}
	//-----
	
	
	//Методы admin
	@GetMapping("/admin")
	public String index(Model model) {
		List<User> users = userService.findAll();
		model.addAttribute("users_list", users);
		return "admin";
	}
	
	@GetMapping("/admin/new")
	public String save(Model model) {
		model.addAttribute("user", new User());
		return "new";
	}
	
	@PostMapping("/admin/new")
	public String create(@ModelAttribute("user") User user, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "new";
		}
		userService.add(user);
		return "redirect:/admin";
	}
	
	@GetMapping("/admin/id/edit")
	public String edit(@RequestParam("id") long id, Model model) {
		model.addAttribute("user", userService.edit(id));
		return "edit";
	}
	
	@PostMapping("/admin/id/update")
	public String update(@RequestParam("id") Long id, @ModelAttribute("user") User user) {
		if (id == null) {
			throw new IllegalArgumentException("ID не может быть null");
		}
		userService.update(id, user);
		return "redirect:/admin";
	}
	
	@PostMapping("/admin/id/delete")
	public String delete(@RequestParam("id") Long id) {
		userService.delete(id);
		return "redirect:/admin";
	}
	
	@GetMapping("/")
	public String login(Model model) {
		return "index";
	}
}

package com.ecommerce.controller;

//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AnonymousAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import com.ecommerce.domain.Admin;
//import com.ecommerce.dto.AdminDto;
//import com.ecommerce.service.AdminService;
//
//@Controller
//public class LoginController {
//
//	@Autowired
//	private AdminService adminService;
//
//	@GetMapping("/login-admin")
//	public String loginForm(Model model) {
//		model.addAttribute("title", "Login");
//		return "login-admin";
//	}
//
//	@RequestMapping("/index-admin")
//	public String home(Model model) {
//		model.addAttribute("title", "Home Page:");
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
//			return "redirect:/login-admin";
//		}
//		return "index-admin";
//	}
//
//	@GetMapping("/register-admin")
//	public String showRegistrationForm(Model model) {
//		model.addAttribute("title", "Register");
//		model.addAttribute("adminDto", new AdminDto()); // Add the adminDto object to the model
//		return "register-admin"; // Return the view name
//	}
//
//	@GetMapping("/forgot-password-admin")
//	public String forgotPassword(Model model) {
//		model.addAttribute("title", "Forgot Password");
//		return "forgot-password-admin";
//	}
//
//	@PostMapping("/register-new-admin")
//	public String addNewAdmin(@Valid @ModelAttribute("adminDto") AdminDto adminDto, BindingResult result, Model model) {
//
//		try {
//
//			if (result.hasErrors()) {
//				model.addAttribute("adminDto", adminDto);
//				result.toString();
//				return "register-admin";
//			}
//			String username = adminDto.getUsername();
//			Admin admin = adminService.findByUsername(username);
//			if (admin != null) {
//				model.addAttribute("adminDto", adminDto);
//				System.out.println("admin not null");
//				model.addAttribute("emailError", "Your email has already been registered!");
//				return "register-admin";
//			}
//			if (adminDto.getPassword().equals(adminDto.getRepeatPassword())) {
//				adminService.save(adminDto);
//				System.out.println("success");
//				model.addAttribute("success", "Register successfully!");
//				model.addAttribute("adminDto", adminDto);
//			} else {
//				model.addAttribute("adminDto", adminDto);
//				model.addAttribute("passwordError", "Your password maybe wrong! Check again!");
//				System.out.println("password not same");
//				return "register-admin";
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			model.addAttribute("errors", "The server has been wrong!");
//		}
//		return "register-admin";
//
//	}
//
//}

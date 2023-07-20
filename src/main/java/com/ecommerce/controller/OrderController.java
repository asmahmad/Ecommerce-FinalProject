package com.ecommerce.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ecommerce.domain.Customer;
import com.ecommerce.domain.Order;
import com.ecommerce.domain.ShoppingCart;
import com.ecommerce.service.CustomerService;
import com.ecommerce.service.OrderService;

@Controller
public class OrderController {
	
	@Autowired
	private CustomerService customerService;
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/check-out")
	public String checkout(Model model, Principal principal) {
		if (principal == null) {
			
			return "redirect:/login";
		}
		String username = principal.getName();
		Customer customer = customerService.findByUsername(username);
		
		if(customer.getPhoneNumber().trim().isEmpty() || customer.getAddress().trim().isEmpty() || customer.getCity().trim().isEmpty() || customer.getCountry().trim().isEmpty()) {
			
			model.addAttribute("customer",customer);
			model.addAttribute("error","You must fill all the information");
			return "account";
			
		}else {
			
			model.addAttribute("customer",customer);
			ShoppingCart cart = customer.getShoppingCart();
			model.addAttribute("cart",cart);
			
		}
		
		return "checkout";
	}
	
	@GetMapping("/order")
	public String placeOrder(Principal principal, Model model) {
		
		if(principal == null) {
			return "redirect:/login";
		}
		
		String username = principal.getName();
		Customer customer = customerService.findByUsername(username);
		List<Order> orderList = customer.getOrders();
		model.addAttribute("orders", orderList);		
		
		return "order";
	}
	
	@GetMapping("/save-order")
	public String saveOrder(Principal principal) {
		if(principal == null) {
			
			return "redirect:/login";
		}
		
		String username = principal.getName();
		Customer customer = customerService.findByUsername(username);
		System.out.println("save-order");
		ShoppingCart cart = customer.getShoppingCart();
		orderService.saveOrder(cart);
		return "redirect:/order";
	}
}

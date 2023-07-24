package com.ecommerce.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ecommerce.dto.ProductDto;
import com.ecommerce.domain.Category;
import com.ecommerce.domain.Customer;
import com.ecommerce.domain.ShoppingCart;
import com.ecommerce.service.CategoryService;
import com.ecommerce.service.CustomerService;
import com.ecommerce.service.ProductService;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Controller
public class HomeController {
	
	private ProductService productService;
	private CustomerService customerService;
	private CategoryService categoryService;

	@RequestMapping(value = { "/index" , "/"}, method = RequestMethod.GET)
	public String home(Model model, Principal principal, HttpSession session) {

		if (principal != null) {
			session.setAttribute("username", principal.getName());
			Customer customer = customerService.findByUsername(principal.getName());
			ShoppingCart cart = customer.getShoppingCart();
			if(cart == null) {
				session.setAttribute("totalItems", 0);
			} else {
				session.setAttribute("totalItems", cart.getTotalItems());
			}
		} else {
			session.removeAttribute("username");
		}

		return "home";
	}

	@GetMapping("/home")
	public String index(Model model) {
		List<Category> categories = categoryService.findAll();
		List<ProductDto> productDtos = productService.findAll();
		model.addAttribute("categories", categories);
		model.addAttribute("products", productDtos);
		return "index";
	}
	@GetMapping("/")
    public String root() {
        return "redirect:/shop/";
    }
}